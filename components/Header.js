import React, { useState } from 'react';
import { View, Text, StyleSheet, TextInput, TouchableHighlight } from 'react-native';

const Header = props => {

    const [buttonPressedL, setButtonPressedL] = useState(true);
    const [buttonPressedR, setButtonPressedR] = useState(true);

    const selectFoot = (buttonText) => {
        if(buttonText == "L"){
            setButtonPressedL(false)
        }else{
            setButtonPressedL(true)
        }
        if(buttonText == "R"){
            setButtonPressedR(false)
        }else{
            setButtonPressedR(true)
        }
    }

    return (
        <View style={styles.both}>
            <View style={styles.containerName}>
                <Text style={styles.text}>Nome do Atleta</Text>
                <TextInput
                    style={styles.inputName}
                />
            </View>

            <View style={styles.containerFoot}>
                <Text style={styles.text}>Front Foot</Text>
                <View style={styles.containerButton}>
                    <View style={styles.button}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressedR? styles.buttonFoot : styles.buttonPressed}
                            onPress={() => selectFoot("R")}>
                            <View><Text style={styles.buttonText}>R</Text></View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.button}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressedL? styles.buttonFoot : styles.buttonPressed}
                            onPress={() => selectFoot("L")}>
                            <View><Text style={styles.buttonText}>L</Text></View>
                        </TouchableHighlight>
                    </View>
                </View>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    both: {
        flexDirection: 'row',
    },

    containerName: {
        flexDirection: 'column',
        padding: 15,
        paddingBottom: 5,
        width: 670,
        marginEnd: 10,
    },

    text: {
        paddingBottom: 5,
    },

    inputName: {
        height: 25,
        borderWidth: 1,
        borderColor: 'black',
        paddingStart: 5,
    },

    containerFoot: {
        flexDirection: 'column',
        padding: 15,
        paddingBottom: 5,
        marginStart: 10,
    },

    containerButton: {
        flexDirection: 'row',
    },

    button: {
        width: 50,
        marginRight: 10,
    },

    buttonFoot: {
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

export default Header;