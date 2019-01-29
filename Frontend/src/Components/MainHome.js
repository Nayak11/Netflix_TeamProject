import React, {Component} from 'react';
import Navbar from './Navbar'
import axios from 'axios';
import url from '../serverurl';

class MainHome extends Component {
    constructor(props){
        super(props);
    }

    componentWillMount(){
        //check sessions
    }


    render(){
        return(
            <div>
                <Navbar />
            </div>
        );
    }
}

export default MainHome;
