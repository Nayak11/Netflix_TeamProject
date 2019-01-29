import React, {Component} from 'react';
import { Redirect } from 'react-router';
import Navbar from './Navbar'
import '../css/profile.css';
import '../css/login.css';
import Dropdown from 'react-dropdown'
import 'react-dropdown/style.css';
import queryString from 'query-string';
import url from '../serverurl';
import swal from "sweetalert";
import axios from "axios/index";


const textStyle = {
    color: "black"
}

class UserProfile extends Component{

    constructor(props){
        super(props);

        this.state = {
         data:[],
            username:"",
            userid:"",
            email:"",
            enddate:"",
            suscription:"",
            redirect:"",
            amount:10,
            options : [

                { value: '1', label: '1 month' },
                { value: '2', label: '2 months' },
                { value: '3', label: '3 months' },
                { value: '4', label: '4 months' },
                { value: '5', label: '5 months' },
                { value: '6', label: '6 months' },
                { value: '7', label: '7 months' },
                { value: '8', label: '8 months' },
                { value: '9', label: '9 months' },
                { value: '10', label: '10 months' },
                { value: '11', label: '11 months' },
                { value: '12', label: '12 months' }

            ],
            selectedMonth:"1",
            movieid:"",
            type:"subscription",
            typeOfuser:""

        }
        this.changeMonth=this.changeMonth.bind(this);
        this.handleOnClick=this.handleOnClick.bind(this);
    }

    componentWillMount(){
        //check session
    }

    componentDidMount() {

        var self = this;
        axios.get(url + "/checksession", {headers : { Authorization :  localStorage.getItem("sessionID")}})
            .then((response) => {
                console.log("In check session of showalluserdetails page: ", response.data);
                this.setState({typeOfuser:response.data.type})
                if(response.data.message != "valid session"){
                    swal("Invalid session please login", "", "warning");
                    self.props.history.push('/login');
                }

            })

        console.log("url parameter",this.props);
        var params=queryString.parse(this.props.location.search);
        var username=params.username;
        var request = {

            headers : {
                Authorization : localStorage.sessionID
            }
        };

        fetch(url+"/getuser",request)
            .then(res => res.json())
            .then(
                (result) => {
                    console.log("/getuser method result",result.message)

                    fetch(url+"/user/" + result.message,request)
                        .then(res => res.json())
                        .then(
                            (result) => {
                                console.log("data for user profile",result)
                                this.setState({
                                    isLoaded: true,
                                    username:result[0].username,
                                    userid:result[0].userid,
                                    email:result[0].email,
                                    subscription:result[0].subscription,
                                    // typeOfuser:result[0].type,
                                    enddate:result[0].enddate
                                });

                            },
                            (error) => {
                                this.setState({
                                    isLoaded: true,
                                    error
                                });
                            }
                        )

                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )

    }
    changeMonth=(e)=>{
        console.log("event is",e);
        this.setState({
            selectedMonth:e.value,
            amount:10*parseInt(e.value)
        })
    }


    handleOnClick = () => {
        // some action...
        // then redirect
        this.props.history.push({pathname:'/payment',search:'?movieid=-1&&payType=subscription&price='+this.state.amount +'&availability=-1',state:{referrer:this.state}})
//?movieid=-1&&payType=subscription&price='+this.state.amount +'&availability=-1
        //return <Redirect to={{pathname:'/payment?movieid=&payType=subscription&price='+this.state.amount +'&availability=',state:{referrer:this.state}}} />;

    }

    render(){

        let changes =null;
        console.log("logged user type in user profile ",this.state.typeOfuser)
        if(this.state.typeOfuser==="USER"){
            console.log("hehre", url)
            changes = (
                <div>
                <div>
                    <label>Select payment period</label>
                    <Dropdown options={this.state.options} onChange={this.changeMonth} value={this.state.selectedMonth}
                              placeholder="Select an option"/>
                </div>
                <div>
               
                <label>Amount to be paid</label>
                &nbsp;
                &nbsp;
                &nbsp;&nbsp;
            <input value={this.state.amount} type="text" style={textStyle}
                   name="title" required autoFocus/>
                </div>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button style={{backgroundColor: "red"}} onClick={this.handleOnClick} className="btn btn-primary"><b>Make
                Payment</b></button>
                </div>
            );

        }
        console.log(" selected month is",this.state.selectedMonth);


        return(

            <div style={{backgroundColor: "black"}}>
            <Navbar history={this.props.history}/>
                <div id="img1">
                    <div class="container">
                        <div class="profile-form">
                            <div id="profile">
                                <h1 id="h1">{this.state.username}'s Profile</h1>
                                &nbsp;
                                {/*<form class="form" onSubmit={this.handleOnClick}>*/}
                                    <div>
                                        <label>User ID</label> <input  value={this.state.userid} type="text" class="form-control" name="title"  autoFocus/>
                                    </div>
                                    &nbsp;
                                    &nbsp;
                                    <div>
                                        <label>User Name</label>

                                        <input value={this.state.username}  type="text" class="form-control" name="title"  autoFocus/>
                                    </div>
                                    &nbsp;&nbsp;
                                    <div>
                                        <label>Email</label>
                                        <input value={this.state.email}  type="text" class="form-control" name="title"  required autoFocus/>
                                    </div>
                                    &nbsp;&nbsp;
                                    <div>
                                        <label>Subscription</label>
                                        <input value={this.state.subscription} type="text" class="form-control" name="title"  required autoFocus/>
                                    </div>
                                    &nbsp;&nbsp;
                                    <div>
                                        <label>End Date</label>
                                        <input value={this.state.enddate} type="text" class="form-control" name="title"  required autoFocus/>
                                    </div>
                                    &nbsp;&nbsp;
                                    {changes}
                                    {/*</form>*/}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
export default UserProfile;