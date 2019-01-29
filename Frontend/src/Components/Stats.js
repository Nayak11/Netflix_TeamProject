import React, {Component} from 'react';
import Navbar from './Navbar.js';
import {ButtonToolbar, DropdownButton, MenuItem } from 'react-bootstrap';
import FusionCharts from 'fusioncharts';
import Charts from 'fusioncharts/fusioncharts.charts';
import ReactFC from 'react-fusioncharts';
import Column2D from 'fusioncharts/fusioncharts.charts';
import FusionTheme from 'fusioncharts/themes/fusioncharts.theme.fusion';
import axios from 'axios';
import url from '../serverurl';
ReactFC.fcRoot(FusionCharts, Column2D, FusionTheme);



class Stats extends Component{

    constructor(props){
        super(props);

        this.state = {
            period: '0',
            users:[],
            movies:[],
            userType:'',
            movieType:''
        }
        this.handleSelect = this.handleSelect.bind(this);
        this.handleSelect1 = this.handleSelect1.bind(this);
    }

    componentDidMount(){

        // top 10 users for period 0
        axios.get(url + "/stats?cls=users&period=0")
        .then((response) => {
            this.setState({
                users: response.data.users,
                userType: 'user'
            })
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        })

        //top 10 movies for period 0
        axios.get(url + "/stats?cls=movies&period=0")
        .then((response) => {
            this.setState({
                movies: response.data.movies,
                movieType: 'movie'
            })
            console.log(response.data);
        }).catch((error) => {
            console.log(error);
        })
    }

    handleSelect(evt){
        if(evt == 0){
            console.log("inside evt 0");
            axios.get(url + "/stats?cls=movies&period=0")
            .then((response) => {
                this.setState({
                    movies: response.data.movies,
                    movieType: 'movie',
                    period: '0'
                })
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            })
        }
        else if(evt ==1){
            console.log("inside evt 1");
            axios.get(url + "/stats?cls=movies&period=1")
            .then((response) => {
                this.setState({
                    movies: response.data.movies,
                    movieType: 'movie',
                    period: '1'
                })
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            })
        }
        else if(evt ==2){
            console.log("inside evt 2");
            axios.get(url + "/stats?cls=movies&period=2")
            .then((response) => {
                this.setState({
                    movies: response.data.movies,
                    movieType: 'movie',
                    period: '2'
                })
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            })
        }
    }

    handleSelect1(evt){
        if(evt == 0){
            console.log("inside handle select 1 evt 0");
            axios.get(url + "/stats?cls=users&period=0")
            .then((response) => {
                this.setState({
                    users: response.data.users,
                    userType: 'user',
                    period: '0'
                })
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            })
        }
        else if(evt ==1){
            console.log("inside handle select 1 evt 1");
            axios.get(url + "/stats?cls=users&period=1")
            .then((response) => {
                this.setState({
                    users: response.data.users,
                    userType: 'user',
                    period: '1'
                })
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            })
        }
        else if(evt ==2){
            console.log("inside handle select 1 evt 2");
            axios.get(url + "/stats?cls=users&period=2")
            .then((response) => {
                this.setState({
                    users: response.data.users,
                    userType: 'user',
                    period: '2'
                })
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            })
        }
    }

  
    render(){
        let topUsers = null;
        let topMovies = null;
       if(this.state.period == '0' &&  this.state.userType ==='user'){
           let dataSource = {
            chart: {
                caption: "Top 10 Users with most # of movie plays",
                "xAxisName": "User",
                "yAxisName": "Number of times played (in count)",
                theme: "fusion",
                "baseFontSize": "20"
            },
            data: this.state.users.map((user) =>({
                label: user.username,
                value:  Math.round(+user.count*100)/100
            }))
           };
           let chartConfigs = {
            id: "users-by-plays",
            type: 'column2d',// The chart type
            width: '700', // Width of the chart
            height: '400', // Height of the chart
            dataFormat: 'json', // Data type
            dataSource: dataSource
           };
           topUsers = <ReactFC {...chartConfigs} />
       }

       if(this.state.period == '1' &&  this.state.userType ==='user'){
        let dataSource = {
         chart: {
             caption: "Top 10 Users with most # of movie plays",
             "xAxisName": "User",
             "yAxisName": "Number of times played (in count)",
             theme: "fusion",
             "baseFontSize": "20"
         },
         data: this.state.users.map((user) =>({
             label: user.username,
             value:  Math.round(+user.count*100)/100
         }))
        };
        let chartConfigs = {
         id: "users-by-plays",
         type: 'column2d',// The chart type
         width: '700', // Width of the chart
         height: '400', // Height of the chart
         dataFormat: 'json', // Data type
         dataSource: dataSource
        };
        topUsers = <ReactFC {...chartConfigs} />
    }

    if(this.state.period == '2' &&  this.state.userType ==='user'){
        let dataSource = {
         chart: {
             caption: "Top 10 Users with most # of movie plays",
             "xAxisName": "User",
             "yAxisName": "Number of times played (in count)",
             theme: "fusion",
             "baseFontSize": "20"
         },
         data: this.state.users.map((user) =>({
             label: user.username,
             value:  Math.round(+user.count*100)/100
         }))
        };
        let chartConfigs = {
         id: "users-by-plays",
         type: 'column2d',// The chart type
         width: '700', // Width of the chart
         height: '400', // Height of the chart
         dataFormat: 'json', // Data type
         dataSource: dataSource
        };
        topUsers = <ReactFC {...chartConfigs} />
    }

       if(this.state.period == '0' && this.state.movieType==='movie'){
        let dataSource = {
         chart: {
             caption: "Top 10 Movies based on number of plays",
             "xAxisName": "Movie",
             "yAxisName": "Number of times played (in count)",
             theme: "fusion",
             "baseFontSize": "20"
         },
         data: this.state.movies.map((movie) =>({
             label: movie.title,
             value:  Math.round(+movie.count*100)/100
         }))
        };
        let chartConfigs = {
         id: "movies-by-plays",
         type: 'column2d',// The chart type
         width: '700', // Width of the chart
         height: '400', // Height of the chart
         dataFormat: 'json', // Data type
         dataSource: dataSource
        };
        topMovies = <ReactFC {...chartConfigs} />
    }

    if(this.state.period == '1' && this.state.movieType==='movie'){
        let dataSource = {
         chart: {
             caption: "Top 10 Movies based on number of plays",
             "xAxisName": "Movie",
             "yAxisName": "Number of times played (in count)",
             theme: "fusion",
             "baseFontSize": "20"
         },
         data: this.state.movies.map((movie) =>({
             label: movie.title,
             value:  Math.round(+movie.count*100)/100
         }))
        };
        let chartConfigs = {
         id: "movies-by-plays",
         type: 'column2d',// The chart type
         width: '700', // Width of the chart
         height: '400', // Height of the chart
         dataFormat: 'json', // Data type
         dataSource: dataSource
        };
        topMovies = <ReactFC {...chartConfigs} />
    }

    if(this.state.period == '2' && this.state.movieType==='movie'){
        let dataSource = {
         chart: {
             caption: "Top 10 Movies based on number of plays",
             "xAxisName": "Movie",
             "yAxisName": "Number of times played (in count)",
             theme: "fusion",
             "baseFontSize": "20"
         },
         data: this.state.movies.map((movie) =>({
             label: movie.title,
             value:  Math.round(+movie.count*100)/100
         }))
        };
        let chartConfigs = {
         id: "movies-by-plays",
         type: 'column2d',// The chart type
         width: '700', // Width of the chart
         height: '400', // Height of the chart
         dataFormat: 'json', // Data type
         dataSource: dataSource
        };
        topMovies = <ReactFC {...chartConfigs} />
    }

        return(
            <div>
                <Navbar history={this.props.history}/>
                <div class="container">
                    <div class="row">
                        <div class="col-lg-4">
                            <h2>Top 10 Movies </h2> 
                             <DropdownButton  bsSize="large" title="Select" id="dropdown-size-large" onSelect={this.handleSelect}>
                                <MenuItem eventKey="0" >24 hours</MenuItem>
                                <MenuItem eventKey="1" >week</MenuItem>
                                <MenuItem eventKey="2" >month</MenuItem>
                            </DropdownButton>
                            {topMovies}
                        </div>
                    </div>
                    <hr/>
                    <hr/>
                    <div>
                        <div class="col-lg-4">
                            <h2>Top 10 Users </h2>
                            <DropdownButton   bsSize="large" title="Select" id="dropdown-size-large" onSelect={this.handleSelect1}>
                                <MenuItem eventKey="0">24 hours</MenuItem>
                                <MenuItem eventKey="1">week</MenuItem>
                                <MenuItem eventKey="2">month</MenuItem>
                            </DropdownButton>
                            {topUsers}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Stats;

