import React, {Component} from 'react';
import Navbar from './Navbar.js';
import { TabContent, TabPane, Nav, NavItem, NavLink } from 'reactstrap';
import FusionCharts from 'fusioncharts';
import Charts from 'fusioncharts/fusioncharts.charts';
import ReactFC from 'react-fusioncharts';
import classnames from 'classnames';
import axios from 'axios';
import url from '../serverurl';
Charts(FusionCharts);

class Scoreboard extends Component{

    constructor(props){
        super(props);

        this.toggle = this.toggle.bind(this);
        this.state = {
            activeTab:'plays',
            movies: [],
            movies1: []
        }
    }

    componentDidMount(){
        //get top movies and highly rated movies 
        axios.get(url + '/scoreboard?cls=plays')
        .then((response) => {
            this.setState({
                movies: response.data.movies
            })
            console.log(this.state.movies);
        }).catch((error) => {
            console.log(error);
        })
        axios.get(url + '/scoreboard?cls=rating')
        .then((response) => {
            console.log(response.data.movies)
            this.setState({
                movies1: response.data.movies
            })
        }).catch((error) => {
            console.log(error);
        })
    }

    toggle(tab) {
	    if (this.state.activeTab !== tab) {
			this.setState({
				activeTab: tab
			});
	    }
	}

    render(){

        let moviesRating = null;
        let moviesPlay = null;
       // console.log(this.state);
        let size = Object.keys(this.state.movies).length;
        let size1 = Object.keys(this.state.movies1).length;
        //console.log(size);
        if(this.state.activeTab === "plays" && size<=10){ // display only top 10???
            let dataSource = {
                chart: {
                    caption: "Top 10 movies by number of plays",
		            "xAxisName": "Movie",
                    "yAxisName": "Number of times played (in count)",
		            theme: "ocean"
                },
                data: this.state.movies.map((movie) => ({
                    label: movie.title,
                    value: Math.round(+movie.count*100)/100
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
            moviesPlay = <ReactFC {...chartConfigs} />
        }
        if(this.state.activeTab === 'rating'& size1 <=10){
            let dataSource = {
                chart: {
                    caption: "Top 10 movies by star ratings",
		            "xAxisName": "Movie",
                    "yAxisName": "Number of star ratings (in count)",
		            theme: "ocean"
                },
                data: this.state.movies1.map((movie) => ({
                    label: movie.title,
                    value: Math.round(+movie.avg*100)/100
                }))
            };
            let chartConfigs = {
                id: "movies-by-rating",
                type: 'column2d',// The chart type
                width: '700', // Width of the chart
                height: '400', // Height of the chart
                dataFormat: 'json', // Data type
                dataSource: dataSource
            };
            moviesRating = <ReactFC {...chartConfigs} />
        }
        return(
            <div>
                <Navbar history={this.props.history}/><br/><br/><br/>
                <div class="container">
                    <div class="row"><div class="col-sm-1"></div>
                        <div class="col-sm-7">
                            <Nav tabs>
                                <NavItem>
                                    <NavLink className={classnames({ active: this.state.activeTab === 'plays' })} onClick={() => { this.toggle('plays'); }}>
                                        Most Popular Movies
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink className={classnames({ active: this.state.activeTab === 'rating' })} onClick={() => { this.toggle('rating'); }}>
                                        Most Highly Rated Movies 
                                    </NavLink>
                                </NavItem>
                            </Nav>
                            <TabContent activeTab={this.state.activeTab}>
                                <TabPane tabId="plays">
                                {moviesPlay}
                                </TabPane>
                                <TabPane tabId="rating">
                                {moviesRating}
                                </TabPane>
                            </TabContent>
                        </div><div class="col-sm-1"></div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Scoreboard;