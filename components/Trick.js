import React, { useState } from 'react';
import { View, StyleSheet, FlatList, Text, TextInput, TouchableHighlight } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import Rolls from '../constants/rolls'
import Tantrums from '../constants/tantrums'
import Raleys from '../constants/raley'

const Trick = props => {

    const [buttonPressedRo, setButtonPressedRo] = useState(true);
    const [buttonPressedRa, setButtonPressedRa] = useState(true);
    const [buttonPressedT, setButtonPressedT] = useState(true);

    const [selectTrick, setSelectTrick] = useState(false);
    const [textSelectTrick, setTextSelectTrick] = useState("");

    const selectTrickHandler = (buttonText) => {
        if(buttonText == "Rolls"){
            setButtonPressedRo(false)
        }else{
            setButtonPressedRo(true)
        }
        if(buttonText == "Raleys"){
            setButtonPressedRa(false)
        }else{
            setButtonPressedRa(true)
        }
        if(buttonText == "Tantrums"){
            setButtonPressedT(false)
        }else{
            setButtonPressedT(true)
        }
        setSelectTrick(true);
        setTextSelectTrick(buttonText);
    }

    const [selectTrick2, setSelectTrick2] = useState(false);
    const [textSelectTrick2, setTextSelectTrick2] = useState("");

    const selectTrickHandler2 = (buttonText) => {
        setSelectTrick2(true);
        setTextSelectTrick2(buttonText);
    }

    const renderFirstLayer = () => {
        if (selectTrick == true && textSelectTrick == "Rolls") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Rolls.layer1Rolls}
                    renderItem={itemData =>
                        <View style={styles.itemMan}>
                            <TouchableHighlight
                                underlayColor='none'
                                onPress={() => selectTrickHandler2(itemData.item)}
                                style={styles.button}>
                                <View><Text style={styles.buttonText}>{itemData.item}</Text></View>
                            </TouchableHighlight>
                        </View>}
                />
            )
        }

        if (selectTrick == true && textSelectTrick == "Tantrums") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Tantrums.layer1Tantrums}
                    renderItem={itemData =>
                        <View style={styles.itemMan}>
                            <TouchableHighlight
                                underlayColor='none'
                                onPress={() => selectTrickHandler2(itemData.item)}
                                style={styles.button}>
                                <View><Text style={styles.buttonText}>{itemData.item}</Text></View>
                            </TouchableHighlight>
                        </View>}
                />
            )
        }

        if (selectTrick == true && textSelectTrick == "Raleys") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Raleys.layer1Raley}
                    renderItem={itemData =>
                        <View style={styles.itemMan}>
                            <TouchableHighlight
                                underlayColor='none'
                                onPress={() => selectTrickHandler2(itemData.item)}
                                style={styles.button}>
                                <View><Text style={styles.buttonText}>{itemData.item}</Text></View>
                            </TouchableHighlight>
                        </View>}
                />
            )
        }

        if (selectTrick == true && textSelectTrick == "") {
            return (<View></View>)
        }
    }

    const renderSecondLayer = () => {
        if (selectTrick2 == true && textSelectTrick2 == "HS Backrolls") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Rolls.layer2HS}
                    renderItem={itemData =>
                        <View style={styles.itemMan}>
                            <TouchableHighlight
                                underlayColor='none'
                                onPress={() => selectTrickHandler2(itemData.item)}
                                style={styles.button}>
                                <View><Text style={styles.buttonText}>{itemData.item}</Text></View>
                            </TouchableHighlight>
                        </View>}
                />
            )
        }
    }

    const [enteredPointE, setEnteredPointE] = useState('');
    const [enteredPointI, setEnteredPointI] = useState('');
    const [enteredPointC, setEnteredPointC] = useState('');

    const pointInputHandlerE = (enteredText) => {
        setEnteredPointE(parseInt(enteredText));
    };

    const pointInputHandlerI = (enteredText) => {
        setEnteredPointI(parseInt(enteredText));
    };

    const pointInputHandlerC = (enteredText) => {
        setEnteredPointC(parseInt(enteredText));
    };

    const result = (enteredPointE + enteredPointI + enteredPointC) * 3.33

    return (
        <View style={styles.both}>
            <View style={styles.containerM}>

                <View style={styles.backF}>

                    <TouchableHighlight
                        underlayColor='none'
                        onPress={() => selectTrickHandler("")}
                        style={styles.button}>
                        <View><Ionicons style={styles.buttonText} name="arrow-back-outline" size={20} /></View>
                    </TouchableHighlight>

                    <View style={styles.fButton}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={styles.button}>
                            <View><Text style={styles.buttonText}>F</Text></View>
                        </TouchableHighlight>
                    </View>

                </View>

                <View style={styles.choices}>
                    <View style={styles.itemMan}>
                        <TouchableHighlight
                            underlayColor='none'
                            onPress={() => selectTrickHandler("Rolls")}
                            style={ buttonPressedRo? styles.button : styles.buttonPressed}>
                            <View><Text style={styles.buttonText}>Rolls</Text></View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.itemMan}>
                        <TouchableHighlight
                            underlayColor='none'
                            onPress={() => selectTrickHandler("Raleys")}
                            style={ buttonPressedRa? styles.button : styles.buttonPressed}>
                            <View><Text style={styles.buttonText}>Raleys</Text></View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.itemMan}>
                        <TouchableHighlight
                            underlayColor='none'
                            onPress={() => selectTrickHandler("Tantrums")}
                            style={ buttonPressedT? styles.button : styles.buttonPressed}>
                            <View><Text style={styles.buttonText}>Tantrums</Text></View>
                        </TouchableHighlight>
                    </View>
                </View>

                <View style={styles.choices}>
                    <View style={styles.itemMan}>
                        {renderFirstLayer()}
                    </View>
                </View>

                <View style={styles.choices}>
                    <View style={styles.itemMan}>
                        {renderSecondLayer()}
                    </View>
                </View>

            </View>

            <View style={styles.containerP}>

                <Text>Pontuação</Text>

                <View style={styles.pointItem}>
                    <Text>E: </Text>
                    <TextInput
                        placeholder='0'
                        keyboardType='numeric'
                        style={styles.numberInput}
                        onChangeText={pointInputHandlerE}
                        value={enteredPointE}
                    />
                </View>

                <View style={styles.pointItem}>
                    <Text>I: </Text>
                    <TextInput
                        placeholder='0'
                        keyboardType='numeric'
                        style={styles.numberInput}
                        onChangeText={pointInputHandlerI}
                        value={enteredPointI}
                    />
                </View>

                <View style={styles.pointItem}>
                    <Text>C: </Text>
                    <TextInput
                        placeholder='0'
                        keyboardType='numeric'
                        style={styles.numberInput}
                        onChangeText={pointInputHandlerC}
                        value={enteredPointC}
                    />
                </View>

                <View style={styles.pointItem}>
                    <Text>Total: </Text>
                    {result ? <Text>{result}</Text> : null}
                </View>

            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    both: {
        flexDirection: 'row',
    },

    containerM: {
        flexDirection: 'row',
        borderWidth: 1,
        borderColor: 'black',
        padding: 15,
        margin: 10,
        width: 670,
        height: 250,
    },

    backF: {
        flexDirection: 'column',
        justifyContent: 'space-between',
    },

    fButton: {
        width: 50,
        marginRight: 10,
    },

    choices: {
        justifyContent: 'center',
    },

    itemMan: {
        padding: 10,
    },

    containerP: {
        flexDirection: 'column',
        justifyContent: 'space-between',
        borderWidth: 1,
        borderColor: 'black',
        padding: 5,
        margin: 10,
        width: 115,
        height: 250,
    },

    pointItem: {
        flexDirection: 'row',
        justifyContent: 'space-around',
    },

    numberInput: {
        height: 20,
        borderWidth: 1,
        borderColor: 'black',
        width: 75,
        paddingStart: 5,
    },

    button: {
        backgroundColor: "dodgerblue",
        paddingVertical: 6,
        paddingHorizontal: 15,
        borderRadius: 25,
        borderWidth: 2,
        borderColor: "white",
    },

    buttonPressed:{
        backgroundColor: "dodgerblue",
        paddingVertical: 6,
        paddingHorizontal: 15,
        borderRadius: 25,
        borderWidth: 2,
        borderColor: "black",
    },

    buttonText: {
        color: 'white',
        textAlign: 'center',
        fontWeight: 'bold',
    },
});

export default Trick;