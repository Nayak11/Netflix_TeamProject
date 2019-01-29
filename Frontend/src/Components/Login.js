import React, {Component} from 'react';
import Navbar from './Navbar'
import '../css/login.css';
import axios from 'axios';
import url from '../serverurl';
import swal from 'sweetalert';
//import localstorage from 'local-storage';

class Login extends Component{

    constructor(props){
        super(props);

        this.state = {
            username: "",
            password: "",
            isLoggedIn: false,
            message: ""
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
    }

    handleChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    componentWillMount(){
        //check session
        var self = this;
        axios.get(url + "/checksession", {headers : { Authorization :  localStorage.getItem("sessionID")}})
        .then((response) => {
            console.log("In check session of login page: ", response.data);
            if(response.data.message == "valid session"){
                self.props.history.push('/home');
            }
        })
    }

    handleLogin(e){
        e.preventDefault();
        console.log("In handle login on login page...", this.state.username, this.state.password);
        var user = {
            username: this.state.username,
            password:this.state.password
        }

        axios.post(url + "/login", user)
        .then((response) =>{
            console.log("In handle login after response on login page...", <response className="data"></response>);
console.log("session id in login ",response.data.message)
            if(response.data.status === "SUCCESS"){
                swal("Login Successfull", "", "success");
                localStorage.setItem("sessionID", response.data.message);
                console.log("Push home")
                this.props.history.push('/home');
            } else {
                swal(response.data.message, "", "warning");
            }
        })
    }

    render(){
        return(

        <div style={{backgroundColor: "black"}}>
            <div id="img1">
                <div class="container">
                    <div class="login-form">
                        <div id="login">
                        <h1>Sign In</h1>
                        &nbsp;
                            <form class="form" onSubmit={this.handleLogin}>
                                <div class="form-group">
                                    <input onChange = {this.handleChange} type="text" class="form-control" name="username" placeholder="Username" required autoFocus/>
                                </div>
                                &nbsp;&nbsp;
                                <div class="form-group ">
                                    <input onChange = {this.handleChange} type="password" class="form-control"  name="password" placeholder="Password" required/>
                                </div>
                                &nbsp;&nbsp;&nbsp;
                                &nbsp;
                                <button style={{backgroundColor : "red"}} onClick = {this.handleLogin}  class="btn btn-primary"><b>Sign In</b></button> 
                            </form> 
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <h3>New to Netflix? <a href="/signup"><span>Sign up now.</span></a></h3>              
                        </div>
                    </div>
                </div>
            </div>
        </div>
        );
    }
}

export default Login;