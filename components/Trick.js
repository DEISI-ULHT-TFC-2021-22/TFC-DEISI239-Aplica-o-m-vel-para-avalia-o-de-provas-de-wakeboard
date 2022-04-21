import React, { useState } from 'react';
import { View, StyleSheet, Button, FlatList } from 'react-native';
import Rolls from '../constants/rolls'
import Tantrums from '../constants/tantrums'
import Raleys from '../constants/raley'

const Trick = props => {

    const [selectTrick, setSelectTrick] = useState(false);
    const [textSelectTrick, setTextSelectTrick] = useState("");

    const selectTrickHandler = (buttonText) => {
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
                    renderItem={itemData => <Button 
                        id={itemData.item.id} 
                        title={itemData.item} 
                        onPress={() => selectTrickHandler2(itemData.item)}
                    />}
                />
            )
        }

        if (selectTrick == true && textSelectTrick == "Tantrums") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Tantrums.layer1Tantrums}
                    renderItem={itemData => <Button id={itemData.item.id} title={itemData.item} />}
                />
            )
        }

        if (selectTrick == true && textSelectTrick == "Raleys") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Raleys.layer1Raley}
                    renderItem={itemData => <Button id={itemData.item.id} title={itemData.item} />}
                />
            )
        }
    }

    const renderSecondLayer = () => {
        if (selectTrick2 == true && textSelectTrick2 == "HS BACKROLLS") {
            return (
                <FlatList
                    keyExtractor={(item, index) => item.id}
                    data={Rolls.layer2HS}
                    renderItem={itemData => <Button id={itemData.item.id} title={itemData.item} />}
                />
            )
        }
    }

    return (
        <View style={styles.both}>
            <View style={styles.containerM}>

                <View style={styles.choices}>
                    <View style={styles.itemMan}>
                        <Button
                            title="Rolls"
                            onPress={() => selectTrickHandler("Rolls")}
                        />
                    </View>
                    <View style={styles.itemMan}>
                        <Button 
                            title="Raleys" 
                            onPress={() => selectTrickHandler("Raleys")}
                        />
                    </View>
                    <View style={styles.itemMan}>
                        <Button
                            title="Tantrums" 
                            onPress={() => selectTrickHandler("Tantrums")}
                        />
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

    choices: {
        justifyContent: 'center',
    },

    itemMan: {
        padding: 10,
    },

    containerP: {
        borderWidth: 1,
        borderColor: 'black',
        padding: 50,
        margin: 10,
        width: 115,
        height: 250,
    },
});

export default Trick;