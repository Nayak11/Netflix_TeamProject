import React, {Component} from 'react';
import '../css/showAllUserDetails.css';
import Navbar from './Navbar.js';
import axios from 'axios';
import url from '../serverurl';
import swal from 'sweetalert';


class ShowAllUserDetails extends React.Component {
    constructor(){
        super();
        this.state = {
            data: [],
            user:"",
            type:"",

            searchIncomplete:""
        };
        this.handleChange=this.handleChange.bind(this);
        this.SearchUserClick=this.SearchUserClick.bind(this);
    }
    componentDidMount() {

        var self = this;
        axios.get(url + "/checksession", {headers : { Authorization :  localStorage.getItem("sessionID")}})
            .then((response) => {
                console.log("In check session of showalluserdetails page: ", response.data);
                this.setState({type:response.data.type})
                if(response.data.message != "valid session"){
                    swal("Invalid session please login", "", "warning");
                    self.props.history.push('/login');
                }

            })
        console.log("url parameter",this.props);

       // console.log("localstorage in showusers ",localStorage);
        fetch(url+"/user")
            .then(res => res.json())
            .then(
                (result) => {
                    console.log("users from db",result);
                    this.setState({
                        isLoaded: true,
                        data: result
                    });

                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
        console.log("session id show all user detaills ",localStorage.sessionID)
    }

    SearchUserClick(){
        console.log("user id is",this.state.user)

        var request = {
            headers : {
                Authorization : localStorage.sessionID
            }
        };
        fetch(url+"/user/"+this.state.user,request)
            .then(res => res.json())
                .then(
                    (result) => {
                        console.log("result in show all users",result);
                        if ((result===null) || (result===undefined)){
                            swal("User doesn't exist", "", "warning");
                        }
                        else{
                            console.log("show all user from db",result);
                            if(result[0]["status"]==="success"){
                                this.setState({
                                    isLoaded: true,
                                    data: result
                                });
                            }
                            else{
                                swal(result[0]["status"], "", "warning");
                                this.props.history.push('/login');
                            }
                        }
                    },
                    (error) => {
                        this.setState({
                            isLoaded: false,
                            error
                        });
                    }
                )
        }


    showUserProfile(data){
        console.log("in user profile click", data);

        var request = {

            headers : {
                Authorization : localStorage.sessionID
            }
        };
        axios.get(url+ "/user/"+ data.username,request)
        .then((response) => {
            console.log(" showUserProfile click ",response.data);
            this.props.history.push('/profile?username=' + data.username);
        }, (error)=>{
            alert("baahhhhh");
        })
    }

    handleChange=(e)=>{
        console.log("event",e.target.value);
        this.setState({user: e.target.value});
    }


    render() {
      if (this.state.data==undefined){
          return null;
        }
        else {

          if (this.state.type === "ADMIN") {
              console.log("type of user in showalluserdetails ",this.state.type)
              return (
                  <div>
                      <Navbar history={this.props.history}/>
                      <div class="user-form">
                          <div id="user-details">
                              <h3 id="search">Search for User.</h3>
                              <div class="form-group">
                                  <input type="text" onChange={this.handleChange} class="form-control" name="SearchUser"
                                         placeholder="Search User" required autoFocus/>
                              </div>
                              <input style={{backgroundColor: "red"}} type="submit" class="btn btn-primary"
                                     onClick={this.SearchUserClick} value="Submit"/>
                          </div>
                      </div>
                      <table className="table table-hover" id="table">
                          <thead>
                          <tr className='table-secondary'>
                              <th>User ID</th>
                              <th>User Name</th>
                              <th>Email</th>
                              <th>Subscription</th>
                              <th>Activated</th>
                          </tr>
                          </thead>
                          <tbody>{this.state.data.map(function (item, key) {
                              return (
                                  <tr key={key} onClick={() => {
                                      this.showUserProfile(item)
                                  }}>
                                      <td>{item.userid}</td>
                                      <td>{item.username}</td>
                                      <td>{item.email}</td>
                                      <td>{item.subscription}</td>
                                      <td>{item.activated}</td>
                                  </tr>
                              )
                          }.bind(this))
                          }
                          </tbody>
                      </table>

                  </div>
              )
          }

          else {

              //swal("You are not allowed to access this page", "", "warning");
              return(

                  <div>

                      <h1>You are not allowed to access this page</h1>
                  </div>
              )
          }
      }


    }
}

export default ShowAllUserDetails;