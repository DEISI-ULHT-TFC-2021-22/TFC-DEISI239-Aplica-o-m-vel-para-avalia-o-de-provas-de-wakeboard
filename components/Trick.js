import React from 'react';
import { View, StyleSheet, Button, Text } from 'react-native';

const Trick = props => {
    return (
        <View style={styles.both}>
            <View style={styles.containerM}>

                <View style={styles.choices}>
                    <View style={styles.itemMan}>
                        <Button title="Rolls" />
                    </View>
                    <View style={styles.itemMan}>
                        <Button title="Raleys" />
                    </View>
                    <View style={styles.itemMan}>
                        <Button title="Tantrums" />
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