import './rating.css'
import React, {Component} from 'react';
import {Form,FormGroup,Col,ControlLabel,Checkbox,Button,FormControl} from 'react-bootstrap/lib';
import TextareaAutosize from 'react-textarea-autosize';
import Navbar from '../Navbar.js';
import axios from 'axios'
import * as qs from 'query-string';
import url from '../../serverurl';
import swal from 'sweetalert';

//Define a Review Component
class Rating extends Component{
    //call the constructor method
    constructor(props){
        //Call the constrictor of Super class i.e The Component
        super(props);
        //maintain the state required for this component
        this.state = { 
            rating      :   null,
            movieComment:   ''
        };
    }


    componentDidMount=()=>{
        //Here we will set the state to the values received from other component.
    }

    ratingChangeHandler=(e)=>{
        console.log(e.target.value);
        this.setState({
            rating : e.target.value
        })
    }

    reviewCommentChangeHandler=(e)=>{
        console.log(e.target.value);
        this.setState({
            movieComment : e.target.value
        })
    }

    submitRating=()=>{

       const parsed = qs.parse(this.props.location.search);
        const ratingDetails={
            movieid     :   parsed.movieid,
            rating      :   this.state.rating,
            review     :   this.state.movieComment
        }
        var self = this;
        console.log("Displaying State",ratingDetails);
        axios.post(url + "/add-review",ratingDetails, {headers : {Authorization : localStorage.getItem("sessionID")}})
            .then(response => {
                if(response.data.status == "SUCCESS"){
                    if(response.data.message == "You need to atleast watch once to review"){
                        swal("You need to atleast watch once to review", "", "warning");
                    } else {
                        swal("review added", "", "success");
                        self.props.history.push("/video/"+parsed.movieid);
                    }
                }
            }).catch(err=>{
                console.log("Inside catch block of submitRating",err);
            })
    }

    render(){
        const { country, region } = this.state;
        const addEdit= (this.state.title==undefined || this.state.title==null) ? "Add a" : "Edit the"
        return(
            <div style={{backgroundColor : "black"}}>
                <Navbar history={this.props.history}/>
                <div id="ratingImage">
                            <div style={{paddingLeft : "30%",paddingBottom : "3%"}}>
                                <img style={{width: "30%" , display: "block"}} src={require('../netflix_logo.png')} alt="Kaye naye display karle!"/>
                            </div>
                            <h2 style={{paddingLeft : "30%", color: "white"}}>REVIEWS</h2>
                            <br/><br/>
                            <span style={{paddingLeft : "30%", color: "white"}}><b>Rating(0-5)</b></span>

                            <div style={{paddingLeft : "29%"}}>
                                <div class="form-group name1 col-md-2"><input value={this.state.rating}  onChange={(e) => this.ratingChangeHandler(e)} 
                                class="form-control" type="number" max="5" min="0" step="1" style={{paddingLeft : "30%"}}/>
                                </div>
                            </div>

                            <br/><br/><br/> <br/>

                            <span style={{paddingLeft : "30%", color: "white"}}><b>Review Comments(optional)</b></span>

                            <div style={{paddingLeft : "30%"}}>
                                <TextareaAutosize
                                style={{width : "60%"}}
                                minRows={7}
                                maxRows={10}
                                defaultValue="Your reviews on movie please......."
                                onChange={(e) => this.reviewCommentChangeHandler(e)} 
                                />
                            </div>

                            <br/><br/>

                            <div  style={{paddingLeft : "30%", paddingBottom : "5%"}}>
                                <Button type="submit" btn btn-primary onClick = {this.submitRating} >Submit</Button>
                            </div>

                            <img style={{width: "100%" , display: "block"}} src={require('../Netflix_Footer.png')} alt="Kaye naye display karle!"/>
                        
                </div>
            </div>
        )
    }
}


//export AddMovie Component
export default Rating;