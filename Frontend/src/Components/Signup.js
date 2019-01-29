import React, {Component} from 'react';
import Navbar from './Navbar'
import '../css/signup.css';
import axios from 'axios';
import url from '../serverurl';
import swal from 'sweetalert';

class Signup extends Component{
    constructor(props){
        super(props);
        this.state = {
            username: "",
            email:"",
            password: "",
            isLoggedIn: false,
            message: "",
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSignup = this.handleSignup.bind(this);
    }

    handleChange(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSignup(e){
        e.preventDefault();
        console.log("In Side Signup handle Submit");
        var user = {
            username: this.state.username,
            email: this.state.email,
            password: this.state.password
        }
        this.setState({
            message: "",
        })
        axios.post(url + '/signup', user)
        .then((response) => {
            console.log(response.data);
            if(response.data.status == "SUCCESS"){
                swal("Signup Successfull", "Check your email to verify account!", "success");
                this.setState({
                    message: response.data.message,
                }, () =>{
                    this.props.history.push("/login");
                })  
            } else {
                swal(response.data.message, "", "warning");
            }
        })
    }


    render(){
        return(
            <div style={{backgroundColor: "black"}}>

                <div class="container" style={{backgroundColor: "white"}}>

                <div id="img1">
                <div class="container">
                    <div class="signup-form">
                       <div id="signup"> 
                            <h3 id="h3">Signup to stream movies of your choice.</h3>
                             &nbsp;
                             <form class="form" onSubmit={this.handleLogin}>
                                <div class="form-group">
                                    <input onChange = {this.handleChange} type="text" class="form-control" name="username" placeholder="Username" required autoFocus/>
                                </div>
                                &nbsp;&nbsp;
                                <div class="form-group">
                                    <input onChange = {this.handleChange} type="email" class="form-control" name="email" placeholder="Email" required />
                                </div>
                                &nbsp;&nbsp;&nbsp;
                                <div class="form-group ">
                                    <input onChange = {this.handleChange} type="password" class="form-control"  name="password" placeholder="Password" required/>
                                </div>
                                &nbsp;&nbsp;&nbsp;
                                &nbsp;
                                <button style={{backgroundColor : "red"}} onClick = {this.handleSignup}  class="btn btn-primary"><b>Sign Up</b></button> 
                            </form>
                           &nbsp;&nbsp;
                           <h3>Already have an account? <a href="/login"><span>Login.</span></a></h3>
                       </div>
                    </div>
                </div>
                </div>
            </div>
            </div>
        );
    }

}
export default Signup