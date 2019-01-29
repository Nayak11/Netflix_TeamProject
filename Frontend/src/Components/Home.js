import React, {Component} from 'react';
import Navbar from './Navbar'
import axios from 'axios';
import url from '../serverurl';
import Select from 'react-select';
//import { Card, CardTitle, CardText, CardImg, CardImgOverlay } from 'reactstrap';
import { Card, CardImg, CardText, CardBody, CardLink,
    CardTitle, CardSubtitle } from 'reactstrap';
import ReactPaginate from 'react-paginate';

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


const options3 = [
    { value: '1', label: '1' },
    { value: '2', label: '2' },
    { value: '3', label: '3' },
    { value: '4', label: '4' },
    { value: '5', label: '5' },
];

class Home extends Component {
   //call the constructor method
   constructor(props){
        //Call the constrictor of Super class i.e The Component
        super(props);
        //maintain the state required for this component
        this.state = { 
            movieName   : '',
            genre       : '',
            rating      : '',
            availability: '',
            actors      : '',
            director    : '',
            movies      : [],
            found       : false,
            endOfResult : "",
            stars       : ''
        };
    }


    componentDidMount(){
        var self = this;
        //check session and type of user and display navbar accordingly
        axios.get(url + "/checksession", {headers : { Authorization : localStorage.getItem("sessionID") }})
        .then((response) => {
            console.log("in check session of homepage", response.data);
            if(response.data.message === "invalid session"){ //change to !== after session is done
                self.props.history.push("/login");
            } else {
                self.setState({type : response.data.type});
            }
        })
    }


    searchMovie=()=>{
        console.log("search movie clicked.....");

        var params = new URLSearchParams();

        if(this.state.movieName!="" && this.state.movieName!=null && this.state.movieName!=undefined){
            let splitArr=this.state.movieName.split(" ");
            splitArr.forEach((ele)=>{
                console.log(ele);
                params.append("keys", ele);
            })
        }

        if(this.state.genre!="" && this.state.genre!=null && this.state.genre.value!=undefined){
            console.log(this.state.genre.value)
            params.append("genre",this.state.genre.value)
        }

        if(this.state.rating!="" && this.state.rating!=null && this.state.rating.value!=undefined){
            console.log(this.state.genre.value)
            params.append("rating",this.state.rating.value)
        }

        if(this.state.availability!="" && this.state.availability!=null && this.state.availability.value!=undefined){
            console.log(this.state.availability.value)
            params.append("availability",this.state.availability.value)
        }


        if(this.state.stars!="" && this.state.stars!=null && this.state.stars.value!=undefined){
            console.log(this.state.stars.value)
            params.append("stars",this.state.stars.value)
        }

        if(this.state.actors!="" && this.state.actors!=null && this.state.actors!=undefined){
            console.log(this.state.actors)
            params.append("actors",this.state.actors)
        }

        if(this.state.director!="" && this.state.director!=null && this.state.director!=undefined){
            console.log(this.state.director)
            params.append("directors",this.state.director)
        }
        
        var request = {
             params: params
        };

        console.log("Data to be sent to backend",request);

        this.setState({
            headerInfo : request
        },()=>{
            axios.get(url+"/search",request)
            .then(response => {
                if(response.data.status === "SUCCESS"){
                    console.log("Successfully received movies from backend", response.data)
                    this.setState({
                        movies : response.data.movies,
                        found  : true
                    })
                }else{
                    console.log("entered into failure")
                }
            }).catch(err=>{
                console.log("Inside catch block of bookingEventHandler",err);
            })
        })
    }

    genreChangeHandler=(selectedOption)=>{
       console.log("Selected Option",selectedOption)
        this.setState({ genre : selectedOption});
    }

    ratingChangeHandler=(selectedOption)=>{
        this.setState({ rating : selectedOption});
    }

    availabilityChangeHandler= (selectedOption) => {
        this.setState({ availability : selectedOption});
    }

    starsChangeHandler= (selectedOption) => {
        this.setState({ stars : selectedOption});
    }

    actorsChangeHandler=(e)=>{
        this.setState({
          actors : e.target.value
        })
      }
  
      directorsChangeHandler=(e)=>{
        this.setState({
          director : e.target.value
        })
      }

      movieChangeHandler=(e)=>{
          this.setState({
              movieName : e.target.value
          })
      }


      handlePageClick = (data) => {

        var params = new URLSearchParams();

        if(this.state.movieName!="" && this.state.movieName!=null && this.state.movieName!=undefined){
            let splitArr=this.state.movieName.split(" ");
            splitArr.forEach((ele)=>{
                console.log(ele);
                params.append("keys", ele);
            })
        }

        if(this.state.genre!="" && this.state.genre!=null && this.state.genre.value!=undefined){
            console.log(this.state.genre.value)
            params.append("genre",this.state.genre.value)
        }

        if(this.state.rating!="" && this.state.rating!=null && this.state.rating.value!=undefined){
            console.log(this.state.genre.value)
            params.append("rating",this.state.rating.value)
        }

        if(this.state.availability!="" && this.state.availability!=null && this.state.availability.value!=undefined){
            console.log(this.state.availability.value)
            params.append("availability",this.state.availability.value)
        }

        
        if(this.state.stars!="" && this.state.stars!=null && this.state.stars.value!=undefined){
            console.log(this.state.stars.value)
            params.append("stars",this.state.stars.value)
        }

        if(this.state.actors!="" && this.state.actors!=null && this.state.actors!=undefined){
            console.log(this.state.actors)
            params.append("actors",this.state.actors)
        }

        if(this.state.director!="" && this.state.director!=null && this.state.director!=undefined){
            console.log(this.state.director)
            params.append("directors",this.state.director)
        }
        
        params.append("page",data.selected)


        var request = {
             params: params
        };

        console.log("Data Selected",data.selected);

        axios.get(url+"/search",request)
        .then(response => {
            if(response.data.status === "SUCCESS"){
                console.log("Successfully received movies from backend", response.data)
                if(response.data.movies.length>0){
                    this.setState({
                        endOfResult : "",
                        movies      : response.data.movies,
                        found       : true
                    })
                }else{
                    this.setState({
                        endOfResult : "You have reached end of search result!!!"
                    })
                }
            }else{
                console.log("entered into failure")
            }
        }).catch(err=>{
            console.log("Inside catch block of bookingEventHandler",err);
        })
      };


    render(){
        let movies=[...this.state.movies]
        let heading=null;
        let pagination=null;
        if(this.movieChangeHandler.length>0 && this.state.found){
            heading=(
                <div>
                    <h2 style={{color : "green"}}>Kindly scroll down to see the search result</h2>
                    <hr/>
                </div>
            )
        }else if(this.movieChangeHandler.length<=0 && !this.state.found){
            heading=(
                <div>
                    <h2 style={{color : "red"}}>No results found!</h2>
                    <hr/>
                </div>
            )
        }
        if(this.state.movies.length>0){
            pagination=(
                <div style={{paddingLeft : "35%"}}>
                    <ReactPaginate previousLabel={"previous"}
                    nextLabel={"next"}
                    breakLabel={"..."}
                    breakClassName={"break-me"}
                    pageCount={this.state.pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={this.handlePageClick}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"} />
                </div>
            )
        }
        return(
            <div style={{backgroundColor: "black"}}>
                <div id="img1">
                    <Navbar history={this.props.history}/>
                    <div class="container" id="home1" style={{paddingTop : "0%"}}>
                        <h1 style={{color : "red"}}>Search Movie</h1>
                        <form className="form-inline my-2 my-lg-0">
                            <div>
                                <input style={{width : "80%", paddingTop : "4%", paddingBottom : "4%"}} className="form-control mr-sm-2
                                iconColour" type="search" onChange={this.movieChangeHandler} name="searchString" placeholder="Find Movie......" aria-label="Search" />
                            </div>

                            <br></br><br></br>

                            <div>
                                <input style={{width : "80%", paddingTop : "3%", paddingBottom : "3%"}} value={this.state.actors} onChange = {this.actorsChangeHandler} type="text" class="form-control"  name="actors" placeholder="Actors" required/>
                            </div>

                            <br></br><br></br>

                            <div>
                                <input style={{width : "80%", paddingTop : "3%", paddingBottom : "3%"}} value={this.state.director}  onChange = {this.directorsChangeHandler} type="text" class="form-control"  name="directors" placeholder="directors" required/>
                            </div>

                            <br></br><br></br>

                            <div style={{width : "90%", paddingLeft : "10%", color : "black"}}>
                                <Select class="form-control"  value={this.state.genre} onChange={this.genreChangeHandler} options={options2} placeholder="Genre"/>
                            </div>

                            <br></br><br></br>

                            <div style={{width : "90%", paddingLeft : "10%", color : "black"}}>
                                <Select class="form-control"  value={this.state.rating} onChange={this.ratingChangeHandler} options={options} placeholder="Rating"/>
                            </div>

                            <br></br> <br></br>

                            <div style={{width : "90%", paddingLeft : "10%", color : "black"}}>
                                <Select class="form-control"  value={this.state.availability} onChange={this.availabilityChangeHandler} options={options1} placeholder="Availability"/>
                            </div>

                            <br></br> <br></br>

                            <div style={{width : "90%", paddingLeft : "10%", color : "black"}}>
                                <Select class="form-control"  value={this.state.stars} onChange={this.starsChangeHandler} options={options3} placeholder="Stars"/>
                            </div>

                            <br></br> <br></br>
                            <hr></hr>
                            <br></br> <br></br>
                            {heading}
                            <button style={{width : "60%",height : "50%", paddingTop : "1%", paddingBottom : "2%", backgroundColor : "red"}}
                            className="btn btn-primary" onClick={this.searchMovie} type="button"><h3>Search</h3></button>
                        </form>
                    </div>
                </div>


                <div style={{paddingLeft : "10%", backgroundColor : "black"}}>
                    <br/>
                    {movies.map(mov=>(
                        <button onClick={() => { this.props.history.push('/video/'+mov.movieid) }}>
                            <div style={{color:"red"}}>
                            <Card>
                                <CardBody>
                                <CardTitle style={{color: "black"}}><h3>{mov.title}</h3></CardTitle>
                                <CardSubtitle>{mov.description}</CardSubtitle>
                                </CardBody>
                                <img width="20%" height="25%" src={require('./SearchBackground.jpg')} alt="Card image cap" />
                                <CardBody>
                                    <CardText style={{color : "black"}}><b>Year:</b>{mov.year}</CardText>
                                    <CardText style={{color : "black"}}><b>Actor:</b>{mov.actors}</CardText>
                                    <CardText style={{color : "black"}}><b>director:</b>{mov.director}</CardText>
                                    <CardText style={{color : "black"}}><b>Availability:</b>{mov.availability}</CardText>
                                    <CardText style={{color : "black"}}><b>Price:</b>{mov.price}</CardText>
                                    <CardText style={{color : "black"}}><b>Avg rating:</b>{mov.stars}</CardText>
                                </CardBody>
                            </Card>
                            <b><hr style={{borderColor : "red"}}/></b>
                            </div>
                        </button>
                    ))}
                </div>
                <div style={{color : "green", paddingLeft: "33%"}}><h2>{this.state.endOfResult}</h2></div>
                {pagination}
            </div>
        );
    }
}

export default Home;
