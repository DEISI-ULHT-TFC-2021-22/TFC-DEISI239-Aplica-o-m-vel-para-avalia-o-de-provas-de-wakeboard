import React, {useState} from 'react';
import { View, Text, StyleSheet, TextInput, Button } from 'react-native';


export default function App() {

  var manobras = {
    raleys: [tsRaleys, hsRaleys],
    rolls: [hsRolls, tsFrontRolls, tsBackrolls],
    tantrums: [straight, bsRotation, fsRotation, surface]
  }

  var tsRaleys = {
    straight: ["TS Raley", "Indy grab + Batwing to blind"],
    bsRotation: ["180 + Indy grab + Batwing to blind"], 
    fsRotation: ["360 + 90210", "540 + Oh Really"],
  }

  var hsRaleys = {
    straight: ["Raley", "Method grab + Hoochie Glide", "Stalefish grab + OHH", "Indy grab + Indy glide", 
    "Nuclear grab + Nuclear glide", "No hander + Suicide Raley" + "Shifty 911"],
    bsRotation: ["180 + Blind Judge", "360 + S-Bend", "360 + Rewind + Vulcan", "360 + Rewind + Melon Grab + Bee Sting",
     "540 + S to blind", "720 + S-Bend 7"],
    fsRotation: ["180 + Krypt", "180 + Method grab + Hoochie Krypt", "360 + 313", "540 + 313 5"],
  }

  

  const [firstChoice, setFirstChoice] = useState(true);

  return (
    <View>

      <View style={styles.header}>
        {/*Input do atleta*/}
        <View>
          <Text>Nome do Atleta</Text>
          <TextInput />
        </View>

        {/*Input do front foot*/}
        <View>
          <Text>Front Foot</Text>
          <TextInput />
        </View>
      </View>

      {/*Histórico de manobras*/}
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

      {/*Manobra*/}
      <View style={styles.both}>
        <View style={styles.containerM}>

          <View style={styles.choices}>
            <View style={styles.itemMan}>
              <Button title="Rolls"/>
            </View>
            <View style={styles.itemMan}>
              <Button title="Raleys"
                onPress={firstChoice ? () => setFirstChoice(false) : () => setFirstChoice(true) } />
            </View>
            <View style={styles.itemMan}>
              <Button title="Tantrums"/>
            </View>
          </View>

          {!firstChoice ? 
          <View style={styles.choices}>

            <View style={styles.itemMan}>
              <Button title="TS Raley"/>
            </View>

            <View style={styles.itemMan}>
              <Button title="HS Raley"/>
            </View>

          </View> : null}

        </View>

        <View style={styles.containerP}>
        </View>
      </View>


      <View style={styles.footer}>
        {/*Footer = Onda + Altura + ??*/}
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


    </View>
  );
}

const styles = StyleSheet.create({
  header: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    padding: 15,
  },

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
    height: 200,
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
    height: 200,
  },

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