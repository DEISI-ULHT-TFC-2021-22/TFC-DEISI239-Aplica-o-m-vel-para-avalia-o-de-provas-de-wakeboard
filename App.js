import { View, Text, StyleSheet, TextInput, Button } from 'react-native';


export default function App() {
  return (
    <View>

      <View style={styles.header}>
        {/*Input do atleta*/}
        <View>
            <Text>Nome do Atleta</Text>
            <TextInput/>
        </View>

        {/*Input do front foot*/}
        <View>
            <Text>Front Foot</Text>
          <TextInput/>
        </View>
      </View>

      {/*Histórico de manobras*/}
      <View>
        <View style={styles.histText}>
          <Text>Histórico de manobras</Text>
        </View>
        <View style={styles.hist}>
          <View style={styles.item}>
            <Button title="HS Back"/>
          </View>
          <View style={styles.item}>
            <Button title="HS Front"/>
          </View>
          <View style={styles.item}>
            <Button title="Tantrum"/>
          </View>
          <View style={styles.item}>
            <Button title="HS Raley"/>
          </View>
        </View>
      </View>

      {/*Manobra*/}
      <View style={styles.both}>
        <View style={styles.containerM}>
          EXEMPLO MANOBRA
        </View>

        <View style={styles.containerP}>
          EXEMPLO PONTUAÇÃO
        </View>
      </View>


      <View style={styles.footer}>
        {/*Footer = Onda + Altura + ??*/}
        <View>
          <Text>Onda</Text>
          
            <View style={styles.histFooter}>
              <View style={styles.itemFooter}>
                <Button title="Icon"/>
              </View>
              <View style={styles.itemFooter}>
                <Button title="Icon"/>
              </View>
              <View style={styles.itemFooter}>
                <Button title="Icon"/>
              </View>
            </View>

        </View>

        <View>
          <Text>Altura</Text>

          <View style={styles.histFooter}>
              <View style={styles.itemFooter}>
                <Button title="Icon"/>
              </View>
              <View style={styles.itemFooter}>
                <Button title="Icon"/>
              </View>
              <View style={styles.itemFooter}>
                <Button title="Icon"/>
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

  item:{
    paddingHorizontal: 5,
  },

  both: {
    flexDirection: 'row',
  },

  containerM:{
    justifyContent: 'center',
    alignItems: 'center',
    alignContent: 'center',
    borderWidth: 1,
    borderColor: 'black',
    padding: 50,
    margin: 10,
    width: 670,
    height: 200,
  },

  containerP:{
    justifyContent: 'center',
    alignItems: 'center',
    alignContent: 'center',
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

  itemFooter:{
    paddingHorizontal: 5,
  },

