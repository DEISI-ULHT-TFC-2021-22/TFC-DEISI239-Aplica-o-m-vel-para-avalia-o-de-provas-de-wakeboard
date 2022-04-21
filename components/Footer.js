import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableHighlight } from 'react-native';
import { Ionicons } from '@expo/vector-icons';
import { MaterialCommunityIcons } from '@expo/vector-icons';

const Footer = props => {
    const [buttonPressed2Up, setButtonPressed2Up] = useState(true);
    const [buttonPressed1Up, setButtonPressed1Up] = useState(true);
    const [buttonPressedDw, setButtonPressedDw] = useState(true);

    const [buttonPressedW3, setButtonPressedW3] = useState(true);
    const [buttonPressedW2, setButtonPressedW2] = useState(true);
    const [buttonPressedW1, setButtonPressedW1] = useState(true);

    const selectWave = (buttonText) => {
        if(buttonText == "W3"){
            setButtonPressedW3(false)
        }else{
            setButtonPressedW3(true)
        }
        if(buttonText == "W2"){
            setButtonPressedW2(false)
        }else{
            setButtonPressedW2(true)
        }
        if(buttonText == "W1"){
            setButtonPressedW1(false)
        }else{
            setButtonPressedW1(true)
        }
    }

    const selectHeight = (buttonText) => {
        if(buttonText == "2Up"){
            setButtonPressed2Up(false)
        }else{
            setButtonPressed2Up(true)
        }
        if(buttonText == "1Up"){
            setButtonPressed1Up(false)
        }else{
            setButtonPressed1Up(true)
        }
        if(buttonText == "Dw"){
            setButtonPressedDw(false)
        }else{
            setButtonPressedDw(true)
        }
    }

    return (
        <View style={styles.footer}>
            <View>
                <Text>Onda</Text>

                <View style={styles.histFooter}>
                    <View style={styles.itemFooter}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressedW3? styles.button : styles.buttonPressed}
                            onPress={() => selectWave("W3")}>
                            <View><MaterialCommunityIcons style={styles.buttonText} name="waves" size={20} /></View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.itemFooter}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressedW2? styles.button : styles.buttonPressed}
                            onPress={() => selectWave("W2")}>
                            <View><Ionicons style={styles.buttonText} name="reorder-two-outline" size={20} /></View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.itemFooter}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressedW1? styles.button : styles.buttonPressed}
                            onPress={() => selectWave("W1")}>
                            <View><MaterialCommunityIcons style={styles.buttonText} name="wave" size={20} /></View>
                        </TouchableHighlight>
                    </View>
                </View>

            </View>

            <View>
                <Text>Altura</Text>

                <View style={styles.histFooter}>
                    <View style={styles.itemFooter}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressed2Up? styles.button : styles.buttonPressed}
                            onPress={() => selectHeight("2Up")}>
                            <View style={styles.arrageArrows}>
                                <Ionicons style={styles.buttonText} name="arrow-up-outline" size={20} />
                                <Ionicons style={styles.buttonText} name="arrow-up-outline" size={20} />
                            </View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.itemFooter}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressed1Up? styles.button : styles.buttonPressed}
                            onPress={() => selectHeight("1Up")}>
                            <View><Ionicons style={styles.buttonText} name="arrow-up-outline" size={20} /></View>
                        </TouchableHighlight>
                    </View>
                    <View style={styles.itemFooter}>
                        <TouchableHighlight
                            underlayColor='none'
                            style={ buttonPressedDw? styles.button : styles.buttonPressed}
                            onPress={() => selectHeight("Dw")}>
                            <View><Ionicons style={styles.buttonText} name="arrow-down-outline" size={20} /></View>
                        </TouchableHighlight>
                    </View>
                </View>

            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    footer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        padding: 15,
    },

    histFooter: {
        flex: 1,
        flexDirection: 'row',
        padding: 15,
    },

    itemFooter: {
        paddingHorizontal: 5,
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

    arrageArrows: {
        flexDirection: 'row',
    }
});

export default Footer;