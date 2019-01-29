import React, {Component} from 'react';
import Navbar from './Navbar'
import '../css/login.css';
import axios from 'axios';
import url from '../serverurl';
import swal from 'sweetalert';
import queryString from 'query-string'
class Verify extends Component{

    constructor(props){
        super(props);

        this.state = {

        }
    }

    handleChange(e) {

    }

    componentWillMount(){
        //check session
        var params=queryString.parse(this.props.location.search);


        var code=params.code;
        var email=params.email
        console.log("params",typeof code,email);
        console.log("url is ",url + "/verify?code="+code+"&email="+email);
        axios.get(url + "/verify?code="+code+"&email="+email)
            .then((response) => {
                console.log("In check session of login page: ", response);
                if (response.data.status=="SUCCESS"){
                    swal("Verification Successfull","", "success");
                }
                else{

                    swal("Verification failed", "", "warning");
                }
                /*
                if(response.data.message !== "invalid session"){
                    this.setState({
                        isLoggedIn: true
                    }, () => {
                        if(this.state.isLoggedIn == true){
                            this.props.history.push('/home');
                        }
                    })
                }*/
            })
    }


    render(){
        return(
            <div style={{backgroundColor: "black"}}>

            </div>
        );
    }
}

export default Verify;