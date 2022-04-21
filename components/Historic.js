import React from 'react';
import { View, Text, StyleSheet, TouchableHighlight } from 'react-native';

const Historic = props => {
    return (
        <View>
            <View style={styles.histText}>
                <Text>Histórico de manobras</Text>
            </View>
            <View style={styles.hist}>
                <View style={styles.item}>
                    <TouchableHighlight
                        underlayColor='none'
                        style={styles.button}>
                        <View><Text style={styles.buttonText}>HS Back</Text></View>
                    </TouchableHighlight>
                </View>
                <View style={styles.item}>
                    <TouchableHighlight
                        underlayColor='none'
                        style={styles.button}>
                        <View><Text style={styles.buttonText}>HS Front</Text></View>
                    </TouchableHighlight>
                </View>
                <View style={styles.item}>
                    <TouchableHighlight
                        underlayColor='none'
                        style={styles.button}>
                        <View><Text style={styles.buttonText}>Tantrum</Text></View>
                    </TouchableHighlight>
                </View>
                <View style={styles.item}>
                    <TouchableHighlight
                        underlayColor='none'
                        style={styles.button}>
                        <View><Text style={styles.buttonText}>HS Raley</Text></View>
                    </TouchableHighlight>
                </View>
            </View>
        </View>

    )
}

const styles = StyleSheet.create({
    histText: {
        paddingHorizontal: 15,
        paddingTop: 15,
    },

    hist: {
        flex: 1,
        flexDirection: 'row',
        padding: 15,
    },

    item: {
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

    buttonText: {
        color: 'white',
        textAlign: 'center',
        fontWeight: 'bold',
    },
});


export default Historic;