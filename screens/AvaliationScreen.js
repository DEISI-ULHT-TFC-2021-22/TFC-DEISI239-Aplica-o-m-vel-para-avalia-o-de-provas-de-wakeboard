import { View } from 'react-native';

import Header from '../components/Header';
import Historic from '../components/Historic';
import Trick from '../components/Trick';
import Footer from '../components/Footer';

const AvaliationScreen = props => {
    return (
        /*Tela de avaliação*/
        <View>

            {/*Componente Header (name + front foot)*/}
            <Header></Header>

            {/*Componente do histórico de manobras*/}
            <Historic></Historic>

            {/*Componente da manobra*/}
            <Trick></Trick>

            {/*Componente Footer (onda + altura)*/}
            <Footer></Footer>

        </View>
    )
}

export default AvaliationScreen;