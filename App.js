import React, { useState } from 'react';
import { View, Text, StyleSheet, TextInput, Button } from 'react-native';


export default function App() {

  var manobras = {
    raleys: [tsRaleys, hsRaleys],
    rolls: [hsBackRolls, tsFrontRolls, tsBackrolls],
    tantrums: [ttStraight, ttBsRotation, ttfsRotation, surface]
  }

  var tsRaleys = {
    straight: ["TS Raley", "Indy grab + Batwing to blind"],
    bsRotation: ["180 + Indy grab + Batwing to blind"],
    fsRotation: ["360 + 90210", "540 + Oh Really"]
  }

  var hsRaleys = {
    straight: ["Raley", "Method grab + Hoochie Glide", "Stalefish grab + OHH", "Indy grab + Indy glide",
      "Nuclear grab + Nuclear glide", "No hander + Suicide Raley" + "Shifty 911"],
    bsRotation: ["180 + Blind Judge", "360 + S-Bend", "360 + Rewind + Vulcan", "360 + Rewind + Melon Grab + Bee Sting",
      "540 + S to blind", "720 + S-Bend 7"],
    fsRotation: ["180 + Krypt", "180 + Method grab + Hoochie Krypt", "360 + 313", "540 + 313 5"],
  }

  var ttStraight = ["Tantrum", "Double + Temper Tantrum"]

  var ttBsRotation = {
    "180": ["Tantrum to blind"],
    "360": ["Moby Dick", "Wrapped + Iron Cross"],
    "540": ["Moby Dick 5"],
    "olé": ["360 + Whirlybird", "360 + Rewind 180 + Crook", "540 + Whirly 5", "720 + Whirly 7", "720 + WhirlyDick"],
    "Back hand olé": ["360 + Spiderman"],
  }

  var ttfsRotation = ["180 + Tantrum to fakie" + "360 + Billion Dolar Baby"]

  var surface = ["Straight + Bel Air", "BS rotation + 180 + Bel Air to blind", "BS roration + olé + 360 + Tweetybird"]

  var hsBackRolls = {
    straight: ["Backroll", "Meican roll", "Double + Double Backroll"],
    bsRotation: ["180 + Roll to blind", "360 + KGB" + "360 + Wrapped + Wrapped KGB", "540 + KGB 5"],
    fsRotation: ["180 + Roll to rever", "180 + Switch + Half Cab roll" + "360 + Mobius", "360 + Wrapped + Blender",
      "540 + Mobius 5", "540 + Wrapped + Discombobulator"]
  }

  var tsFrontRolls = {
    straight: ["TS Frontroll"],
    bsRotation: ["180 + Tootsie roll", "360 + Dum dum", "540 + Dum dum 5"],
    fsRotation: ["180 + Scarecrow", "180 + Double + The Trifecta", "180 + Rewind 180 + Elephant", "360 + Crow Mobe",
      "360 + Switch + Skeezer", "540 + Crow 5", "720 + Crow 7", "olé + 360 + Diesel", "olé + 540 + Big worm"],
    surface: ["FS rotation + 180 + Eggroll"],
  }

  var tsBackrolls = {
    straight: ["TS Backroll", "Double + Double TS Backroll"],
    bsRotation: ["180 + G-Spot", "180 + Wrapped + Special K", "360 + Blind Pete", "360 + Wrapped + Slurpee"],
    fsRotation: ["180 + TS Roll to rever", "360 + Pete Rose", "360 + Switch + X-mobe", "540 + Pete Rose 5"]
  }

  const [firstChoice, setFirstChoice] = useState(false);
  const [category, setCategory] = useState("")

  const raleyButton = () => {
    setFirstChoice(true)
    setCategory("Raleys")
  }

  const rollsButton = () => {
    setFirstChoice(true)
    setCategory("Rolls")
  }

  const tantrumButton = () => {
    setFirstChoice(true)
    setCategory("Tantrums")
  }

  /*const createChoices = category => {
    const views = [];
    if (category.ts.length == raleys.ts.length) {
      for (const [key, value] of Object.entries(raleys)) {
        views.push(
          <View style={styles.itemMan}>
            <Button title={key}
              onPress = {() => {}}/>
          </View>
        )
      }
    }

    return views;
  }*/

  const createChoices = category => {
    const views = []
    if (category == "Rolls") {
      views.push(
        <>
          <View style={styles.itemMan}>
            <Button title="HS Backrolls" />
          </View>
          <View style={styles.itemMan}>
            <Button title="TS Frontrolls" />
          </View>
          <View style={styles.itemMan}>
            <Button title="TS Backrolls" />
          </View>
        </>
      )
    } else if (category == "Raleys") {
      views.push(
        <>
          <View style={styles.itemMan}>
            <Button title="TS Raleys" />
          </View>
          <View style={styles.itemMan}>
            <Button title="HS Raleys" />
          </View>
        </>
      )
    } else if (category == "Tantrums") {
      views.push(
        <>
          <View style={styles.itemMan}>
            <Button title="Straight" />
          </View>
          <View style={styles.itemMan}>
            <Button title="BS Rotation" />
          </View>
          <View style={styles.itemMan}>
            <Button title="FS Rotation" />
          </View>
          <View style={styles.itemMan}>
            <Button title="Surface" />
          </View>
        </>
      )
    }

    return views
  }



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
              <Button title="Rolls" onPress={rollsButton} />
            </View>
            <View style={styles.itemMan}>
              <Button title="Raleys" onPress={raleyButton} />
            </View>
            <View style={styles.itemMan}>
              <Button title="Tantrums" onPress={tantrumButton}/>
            </View>
          </View>

          <View style={styles.choices}>
            {firstChoice ? createChoices(category) : null}
          </View>

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