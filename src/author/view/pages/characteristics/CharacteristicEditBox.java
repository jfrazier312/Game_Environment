package author.view.pages.characteristics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import author.view.pages.characteristics.CharacteristicFactory.AcceptedParameterTypes;
import author.view.util.BooleanSelector;
import author.view.util.NumberFieldBox;
import author.view.util.TextFieldBox;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class CharacteristicEditBox {

	private Pane myPane;
	private CharacteristicFactory myFactory;
	
	private Map<String, BooleanSelector> myBoolSelectList;
	private Map<String, NumberFieldBox> myIntegerFieldList;
	private Map<String, NumberFieldBox> myDoubleFieldList;
	private Map<String, TextFieldBox> myTextFieldBoxList;

	CharacteristicEditBox(Sprite aSprite, String aName) {
		myFactory = new CharacteristicFactory(aName, aSprite);
		
		myPane = new VBox();
		myBoolSelectList = new HashMap<>();
		myDoubleFieldList = new HashMap<>();
		myIntegerFieldList = new HashMap<>();
		myTextFieldBoxList = new HashMap<>();

		buildSelectors();
	}

	void buildSelectors(){
		Map<String, AcceptedParameterTypes> paramMap = myFactory.getParameterTextToTypeMap();
		
		for( Entry<String, AcceptedParameterTypes> e: paramMap.entrySet()) {
			
			switch (e.getValue()) {
			case BOOL:
				addBooleanSelectors(e.getKey(), new BooleanSelector(e.getKey()) );
				break;
			case INT:
				addIntegerBox(e.getKey(), new NumberFieldBox(e.getKey()) );
				break;
			case DOUBLE:
				addDoubleBox(e.getKey(), new NumberFieldBox(e.getKey()) );
				break;
			case STRING:
				addTextBoxes(e.getKey(), new TextFieldBox(e.getKey()) );
				break;
			case UNACCEPTABLE:
			case SPRITE:
			default:
				break;
			}
		}
	}
	
	private void addTextBoxes( String aText, TextFieldBox aTextFieldBox ){
			myTextFieldBoxList.put(aText, aTextFieldBox);
			myPane.getChildren().add(aTextFieldBox.getPane());
	}
	
	private void addBooleanSelectors( String aText, BooleanSelector aBooleanSelector ){
		myBoolSelectList.put(aText, aBooleanSelector);
		myPane.getChildren().add(aBooleanSelector.getPane());
	}
	
	private void addIntegerBox(String aText, NumberFieldBox aNumberField ){
		myIntegerFieldList.put(aText, aNumberField);
		myPane.getChildren().add(aNumberField.getPane());
	}
	
	private void addDoubleBox(String aText, NumberFieldBox aNumberField){
		myDoubleFieldList.put(aText, aNumberField);
		myPane.getChildren().add(aNumberField.getPane());
	}
	
	Pane getPane(){
		return myPane;
	}	

	Characteristic getCharacteristic(){
		Map<String, Object> textToValueMap = new HashMap<>();
		
		myBoolSelectList.forEach( (s, b) -> textToValueMap.put(s, b.getBoolean()) );
		myIntegerFieldList.forEach( (s, n) -> textToValueMap.put(s, n.getInteger()));
		myDoubleFieldList.forEach( (s, d) -> textToValueMap.put(s, d.getDouble()));
		myTextFieldBoxList.forEach( (s, t) -> textToValueMap.put(s, t.getText()));
		
		return myFactory.getCharacteristicInstance(textToValueMap);
	}
}
