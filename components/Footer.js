import React from 'react';
import { View, Text, StyleSheet, Button } from 'react-native';

const Footer = props => {
    return (
        <View style={styles.footer}>
            <View>
                <Text>Onda</Text>

                <View style={styles.histFooter}>
                    <View style={styles.itemFooter}>
                        <Button title="Icon" />
                    </View>
                    <View style={styles.itemFooter}>
                        <Button title="Icon" />
                    </View>
                    <View style={styles.itemFooter}>
                        <Button title="Icon" />
                    </View>
                </View>

            </View>

            <View>
                <Text>Altura</Text>

                <View style={styles.histFooter}>
                    <View style={styles.itemFooter}>
                        <Button title="Icon" />
                    </View>
                    <View style={styles.itemFooter}>
                        <Button title="Icon" />
                    </View>
                    <View style={styles.itemFooter}>
                        <Button title="Icon" />
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
  });

export default Footer;