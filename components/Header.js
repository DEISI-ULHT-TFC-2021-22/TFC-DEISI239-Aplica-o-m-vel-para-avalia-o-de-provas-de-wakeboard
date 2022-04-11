import React, {useState} from 'react';
import { View, Text, StyleSheet, TextInput, Button } from 'react-native';

const Header = props => {
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
                        <Button title="R"/>
                    </View>
                    <View style={styles.button}>
                        <Button title="L"/>
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

    inputName:{
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
});

export default Header;