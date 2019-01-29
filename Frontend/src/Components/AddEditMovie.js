import React, {Component} from 'react';
import '../App.css';
import {Redirect} from 'react-router';
import {Link} from 'react-router-dom';
import {DropdownButton,ButtonToolbar,MenuItem} from 'react-bootstrap/lib';
import { CountryDropdown, RegionDropdown, CountryRegionData } from 'react-country-region-selector';
import Select from 'react-select';
import 'react-dropdown/style.css';
import Navbar from './Navbar.js';
import axios from 'axios';
import * as qs from 'query-string';
import url from '../serverurl';
import swal from 'sweetalert';

const options = [
    { value: 'PG', label: 'PG' },
    { value: 'PG-13', label: 'PG-13' },
    { value: 'R', label: 'R' },
    { value: 'G', label: 'G' },
    { value: 'NC-17', label: 'NC-17' }
];

const options1 = [
    { value: 'Free', label: 'Free' },
    { value: 'SubscriptionOnly', label: 'SubscriptionOnly' },
    { value: 'PayPerViewOnly', label: 'PayPerViewOnly' },
    { value: 'Paid', label: 'Paid' }
];

const options2 = [
    { value: 'Comedy', label: 'Comedy' },
    { value: 'Sci-fi', label: 'Sci-fi' },
    { value: 'Horror', label: 'Drama' },
    { value: 'Action and Adventure', label: 'Action and Adventure' },
    { value: 'Romance', label: 'Romance' },
    { value: 'Thriller', label: 'Thriller' },
    { value: 'Drama', label: 'Drama' },
    { value: 'Mystery', label: 'Mystery' },
    { value: 'Crime', label: 'Crime' },
    { value: 'Animation', label: 'Animation' },
    { value: 'Adventure', label: 'Adventure' },
    { value: 'Fantasy', label: 'Fantasy' },
    { value: 'Comedy-Romance', label: 'Comedy-Romance' },
    { value: 'Action-Comedy', label: 'Action-Comedy' },
    { value: 'Superhero', label: 'Superhero' }
]

//Define a AddMovie Component
class AddMovie extends Component{
    //call the constructor method
    constructor(props){
        //Call the constrictor of Super class i.e The Component
        super(props);
        //maintain the state required for this component
        this.state = { 
            title       : null,
            genre       : '',
            studioName  : null,
            synopsis    : null,
            imageURL    : null,
            movieURL    : null,
            actors      : null,
            director    : null,
            country     : null,
            rating      : '',
            availability: '',
            price       : null,
            year        : null,
            type        : '',
            filled      : ''
        };

    }

    componentDidMount=()=>{

        console.log(this.props.match.params.id);

        axios.get(url + "/checksession", {headers : { Authorization : localStorage.getItem("sessionID") }})
        .then((response) => {
            console.log("in check session of navbar", response.data);
            if(response.data.message === "invalid session"){ //change to !== after session is done
                console.log('invalid session');
            } else {
                this.setState({type : response.data.type});
            }
        })

        if(this.props.match.params.id>0){
            axios.get(url + "/movie",{
                params: {
                    movieid : this.props.match.params.id
                }
            }).then(response => {
                console.log(response)
                if(response.status == 200){
                    console.log("Successfully fetched movie details",response.data)
                    this.setState({
                        //{value : this.state.genre, label: this.state.genre}
                        title       : response.data.title,
                        genre       : {value : response.data.genre, label: response.data.genre},
                        studioName  : response.data.studio,
                        synopsis    : response.data.synopsis,
                        imageURL    : response.data.image,
                        movieURL    : response.data.movie,
                        actors      : response.data.actors,
                        director    : response.data.director,
                        country     : response.data.country,
                        rating      : {value : response.data.rating, label: response.data.rating},
                        availability: {value : response.data.availability, label: response.data.availability},
                        price       : response.data.price,
                        year        : response.data.year
                    })
                }else{
                    console.log("Something went wrong while fetching movie details")
                }
            }).catch(err=>{
                console.log("Catch block,Something went wrong while fetching movie details",err);
            })
        }
    }

    titleChangeHandler=(e)=>{
        this.setState({
            title : e.target.value
        })
    }

    genreChangeHandler=(selectedOption)=>{
        this.setState({ genre : selectedOption});
    }

    studioNameChangeHandler=(e)=>{
        this.setState({
            studioName : e.target.value
        })
    }

    synopsisChangeHandler=(e)=>{
      this.setState({
        synopsis : e.target.value
      })
    }

    imageURLChangeHandler=(e)=>{
      this.setState({
        imageURL : e.target.value
      })
    }

    movieURLChangeHandler=(e)=>{
      this.setState({
        movieURL : e.target.value
      })
    }

    actorsChangeHandler=(e)=>{
      this.setState({
        actors : e.target.value
      })
    }

    directorChangeHandler=(e)=>{
      this.setState({
        director : e.target.value
      })
    }

    selectCountry (val) {
        this.setState({ country: val });
    }

    priceChangeHandler=(e)=>{
      this.setState({price : e.target.value})
    }

    ratingChangeHandler=(selectedOption)=>{
        this.setState({ rating : selectedOption});
    }

    availabilityChangeHandler= (selectedOption) => {
        console.log("selected option",selectedOption)
        this.setState({ availability : selectedOption});
    }

    yearChangeHandler= (e) => {
        //console.log("selected option",selectedOption)
        this.setState({ year : e.target.value});
    }

    submitMovie=()=>{
        let movieDetails={
            // title       : this.state.title,
            // genre       : this.state.genre,
            // studioName  : this.state.studioName,
            // synopsis    : this.state.synopsis,
            // imageURL    : this.state.imageURL,
            // movieURL    : this.state.movieURL,
            // actors      : this.state.actors,
            // director    : this.state.director,
            // country     : this.state.country,
            // rating      : this.state.rating.value,
            // availability: this.state.rating.availability,
            // price       : this.state.price
        }

        console.log("...........",this.state.country,this.state.director)

        let counter=0;

        if(this.state.title!="" && this.state.title!=null && this.state.title!=undefined){
            movieDetails.title=this.state.title;
            counter++;
        }

        if(this.state.genre.value!="" && this.state.genre.value!=null && this.state.genre.value!=undefined){
            movieDetails.genre=this.state.genre.value;
            counter++;
        }

        if(this.state.synopsis!="" && this.state.synopsis!=null && this.state.synopsis!=undefined){
            movieDetails.synopsis=this.state.synopsis;
            counter++;
        }

        if(this.state.studioName!="" && this.state.studioName!=null && this.state.studioName!=undefined){
            movieDetails.studio=this.state.studioName;
            counter++;
        }

        if(this.state.imageURL!="" && this.state.imageURL!=null && this.state.imageURL!=undefined){
            movieDetails.image=this.state.imageURL;
            counter++;
        }

        if(this.state.movieURL!="" && this.state.movieURL!=null && this.state.movieURL!=undefined){
            movieDetails.movie=this.state.movieURL;
            counter++;
        }

        if(this.state.actors!="" && this.state.actors!=null && this.state.actors!=undefined){
            movieDetails.actors=this.state.actors;
            counter++;
        }

        if(this.state.director!="" && this.state.director!=null && this.state.director!=undefined){
            movieDetails.director=this.state.director;
            counter++;
        }

        if(this.state.country!="" && this.state.country!=null && this.state.country!=undefined){
            movieDetails.country=this.state.country;
            counter++;
        }

        if(this.state.rating.value!="" && this.state.rating.value!=null && this.state.rating.value!=undefined){
            movieDetails.rating=this.state.rating.value;
            counter++;
        }

        if(this.state.availability.value!="" && this.state.availability.value!=null && this.state.availability.value!=undefined){
            console.log(this.state.availability,this.state.availability.value)
            movieDetails.availability=this.state.availability.value;
            counter++;
        }

        if(this.state.price!="" && this.state.price!=null && this.state.price!=undefined){
            movieDetails.price=this.state.price;
            counter++;
        }

        if(this.state.year!="" && this.state.year!=null && this.state.year!=undefined){
            movieDetails.year=this.state.year;
            counter++;
        }


        console.log("Displaying State",movieDetails);
        console.log("Counter Value=",counter)

        //const parsed = qs.parse(this.props.location.search);
        if(counter===13){
            if(this.props.match.params.id>0){
                axios.post(url + "/update-movie?movieid="+this.props.match.params.id,movieDetails)
                    .then(response => {
                        if(response.data.status === "SUCCESS"){
                            console.log("Received success from the backend after successfully inserting the booking record",response.data)
                            this.setState({
                                success : "You have successfully made the booking!!!!",
                                count   : 1,
                                filled:''
                            })
                            console.log("Edited movie",this.props.match.params.id)
                            this.props.history.push("/video/"+this.props.match.params.id)
                        }else{
                            console.log("entered into failure")
                        }
                    }).catch(res=>{
                        console.log("Inside catch block of bookingEventHandler",res);
                    })
            } else {
                axios.post(url + "/add-movie",movieDetails)
                    .then(response => {
                        if(response.data.status === "SUCCESS"){
                            console.log("Received success from the backend after successfully inserting the booking record",response.data)
                            this.setState({
                                success : "You have successfully made the booking!!!!",
                                count   : 1,
                                filled:''
                            })
                            console.log("Added movie",response.data.type)
                            this.props.history.push("/video/"+response.data.type)
                        }else{
                            console.log("entered into failure")
                        }
                    }).catch(res=>{
                        console.log("Inside catch block of bookingEventHandler",res);
                    })
            }
        }else{
            this.setState({
                filled : "Please fill in all the details, before adding/editing a movie"
            })
        }
    }

    deleteMovie = () =>{
        console.log("in delete movie: ", this.props.match.params.id);
        axios.get(url + "/deletemovie?movieid=" + this.props.match.params.id, {headers : { Authorization : localStorage.getItem("sessionID") }})
        .then(response => {
            console.log(response.data);
            if(response.data.status === "SUCCESS"){
                console.log("in delete movie request");
                swal("Movie deleted sucessfully", "", "success");
                this.props.history.push("/home");
            }
        }).catch(response => {
            console.log(response);
        })
    }


    render(){
        let changes = null;
        const { country, region } = this.state;
        const addEdit= (this.props.match.params.id>0) ? "Edit the" : "Add the"
        const add = (this.props.match.params.id>0) ? "Update" : "Add"
        console.log(this.state.type);
        if(this.state.type === 'ADMIN' && this.props.match.params.id > 0){
            changes = (
                <button style={{backgroundColor : "red"}} onClick = {this.deleteMovie}  class="btn btn-primary"><b>Delete</b></button>                 
            );   
        } else {
            changes = null;
        }
        return(
            <div style={{backgroundColor: "black"}}>
                <Navbar history = {this.props.history}/>
                <div id="img">
                <div class="container">
                    <div style={{color : "red"}}><h3>{this.state.filled}</h3></div>
                    <h1 style={{color : "red"}}>{addEdit} Movie</h1>
                    <div class="login-form">
                        <div id="signupAdd">
                                <div class="form-group">
                                    <input value={this.state.title} onChange = {this.titleChangeHandler} type="text" class="form-control" name="title" placeholder="Title" required/>
                                </div>

                                <div class="form-group" style={{color : "black"}}>
                                     <Select class="form-control"  value={this.state.genre} onChange={this.genreChangeHandler} options={options2} placeholder="Genre"/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.studioName} onChange = {this.studioNameChangeHandler} type="text" class="form-control"  name="studioName" placeholder="Studio Name" required/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.synopsis} onChange = {this.synopsisChangeHandler} type="text" class="form-control"  name="synopsis" placeholder="Synopsis" required/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.imageURL} onChange = {this.imageURLChangeHandler} type="text" class="form-control"  name="imageURL" placeholder="Image URL" required/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.movieURL} onChange = {this.movieURLChangeHandler} type="text" class="form-control"  name="movieURL" placeholder="Movie URL" required/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.actors} onChange = {this.actorsChangeHandler} type="text" class="form-control"  name="actors" placeholder="Actors" required/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.director}  onChange = {this.directorChangeHandler} type="text" class="form-control"  name="director" placeholder="Director" required/>
                                </div>

                                <div class="form-group ">
                                    <CountryDropdown class="form-control"  value={country} onChange={(val) => this.selectCountry(val)}  />
                                </div>

                                <div class="form-group" style={{color : "black"}}>
                                    <Select class="form-control"  value={this.state.rating} onChange={this.ratingChangeHandler} options={options} placeholder="Rating"/>
                                </div>

                                <div class="form-group" style={{color : "black"}}>
                                    <Select class="form-control"  value={this.state.availability} onChange={this.availabilityChangeHandler} options={options1} placeholder="Availability"/>
                                </div>

                                <div class="form-group ">
                                    <input value={this.state.price} onChange = {this.priceChangeHandler} type="text" class="form-control"  name="price" placeholder="Price" required/>
                                </div>

                                <div className="form-group ">
                                    <input value={this.state.year} onChange={this.yearChangeHandler} type="text"
                                           className="form-control" name="price" placeholder="Year" required/>
                                </div>
                                <button style={{backgroundColor : "red"}} onClick = {this.submitMovie}  class="btn btn-primary"><b>{add}</b></button>
                                &nbsp;&nbsp;   
                                {changes}              
                        </div>
                    </div>
                </div>
                </div>
            </div>
        )
    }
}
//export AddMovie Component
export default AddMovie;