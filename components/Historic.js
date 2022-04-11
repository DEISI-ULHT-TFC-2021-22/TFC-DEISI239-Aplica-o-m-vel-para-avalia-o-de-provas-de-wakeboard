import React from 'react';
import { View, Text, StyleSheet, Button } from 'react-native';

const Historic = props => {
    return (
        <View>
            <View style={styles.histText}>
                <Text>Histórico de manobras</Text>
            </View>
            <View style={styles.hist}>
                <View style={styles.item}>
                    <Button title="HS Back" />
                </View>
                <View style={styles.item}>
                    <Button title="HS Front" />
                </View>
                <View style={styles.item}>
                    <Button title="Tantrum" />
                </View>
                <View style={styles.item}>
                    <Button title="HS Raley" />
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
});


export default Historic;