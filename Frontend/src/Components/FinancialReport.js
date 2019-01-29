import React, {Component} from 'react';
import Navbar from './Navbar.js';
import  '../css/financial.css';
import url from '../serverurl';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import moment from 'moment';
import 'react-datepicker/dist/react-datepicker.css';


class FinancialReport extends Component{
    constructor(props){
        super(props);
        this.state = {
            distPayPerViewUsers: "",
            uniqueSubscriptionUsers:"",
            revenue:"",
            subscriptionRevenue:"",
            totalNumberOfUsers:"",
            uniquePayPerViewUsers:"",
            payperviewRevenue:"",
            uniqueActiveUsers:"",
            Date: moment(),
            year2:2018,
            month2:11,
            dateforpicker:"2018/12/05"
        };

        this.handleChangeDate = this.handleChangeDate.bind(this);
    }


    handleChangeDate(e) {
        console.log("event is ",e);
        var d=e.toString().split(" ");
        var monthDict={
            'Jan' : 1,
            'Feb' : 2,
            'Mar' : 3,
            'Apr' : 4,
            'May' : 5,
            'Jun' : 6,
            'Jul' : 7,
            'Aug' : 8,
            'Sep' : 9, 
            'Oct' : 10,
            'Nov' : 11,
            'Dec' : 12
    }

    this.setState({dateforpicker:d[3]+'/'+monthDict[d[1]]+'/'+d[2]})
    var month1=monthDict[d[1]]-1; 
        var year1=d[3];
        console.log("d is" , month1,year1);
        console.log("state is" , this.state.month2,this.state.year2)
        let data = {
            month: parseInt(month1),
            year:parseInt(year1)
        }
        axios.post(url + "/financial", data)
        .then((response) => {
            console.log("data is: " + response.data[0].uniqueSubscriptionUsers);
            this.setState({
                distPayPerViewUsers: response.data[0].distPayPerViewUsers,
                uniqueSubscriptionUsers: response.data[0].uniqueSubscriptionUsers,
                revenue: response.data[0].revenue,
                subscriptionRevenue: response.data[0].subscriptionRevenue,
                totalNumberOfUsers: response.data[0].totalNumberOfUsers,
                uniquePayPerViewUsers: response.data[0].uniquePayPerViewUsers,
                payperviewRevenue: response.data[0].payperviewRevenue,
                uniqueActiveUsers: response.data[0].uniqueActiveUsers
            })  
        }, (error) => { 
            console.log(error);
        })
    }


    componentDidMount(){
        console.log("in did mount");
        console.log("state is" , this.state.month2,this.state.year2)
        let data = {
            month: this.state.month2,
            year:this.state.year2
        }
        axios.post(url + "/financial", data)
        .then((response) => {
            console.log("data is: " + response.data[0].uniqueSubscriptionUsers);
            this.setState({
                distPayPerViewUsers: response.data[0].distPayPerViewUsers,
                uniqueSubscriptionUsers: response.data[0].uniqueSubscriptionUsers,
                revenue: response.data[0].revenue,
                subscriptionRevenue: response.data[0].subscriptionRevenue,
                totalNumberOfUsers: response.data[0].totalNumberOfUsers,
                uniquePayPerViewUsers: response.data[0].uniquePayPerViewUsers,
                payperviewRevenue: response.data[0].payperviewRevenue,
                uniqueActiveUsers: response.data[0].uniqueActiveUsers
            })  
        }, (error) => { 
            console.log(error);
        })
    }


    render(){
        console.log("state1 is" , this.state.month2,this.state.year2)
        return(
            <div>
                <Navbar history={this.props.history}/>
                <div class="container">
                <h2 id = "financial">Financial Report</h2>
                <div class="row">
                    <div class="col-lg-6">
                        <h3>Select Month and Year:</h3>
                        <DatePicker name="date" value={this.state.dateforpicker}  onChange={this.handleChangeDate} />
                    </div>
                </div> 
                <table className="table table-hover" id="table1"> 
                <thead>
                    <tr className='table-secondary'>
                       <th>Metrics</th>
                       <th>Count</th>
                   </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Number of Unique Subscription Users</td>
                        <td>{this.state.uniqueSubscriptionUsers}</td>
                    </tr>
                    <tr>
                        <td>Number of Unique Pay per View Users</td>
                        <td>{this.state.uniquePayPerViewUsers}</td>
                    </tr>
                    <tr>
                        <td>Total Unique Active Users</td>
                        <td>{this.state.uniqueActiveUsers}</td>
                    </tr>
                    <tr>
                        <td>Total Unique Users</td>
                        <td>{this.state.totalNumberOfUsers}</td>
                    </tr>
                    <tr>
                        <td>Monthly Subscription Income</td>
                        <td>{this.state.subscriptionRevenue}</td>
                    </tr>
                    <tr>
                        <td>Monthly Pay per View Income</td>
                        <td>{this.state.payperviewRevenue}</td>
                    </tr>
                    <tr>
                        <td>Monthly Total Income</td>
                        <td>{this.state.revenue}</td>
                    </tr>
                </tbody>
                </table>
                </div>
            </div>
        );
    }
}

export default FinancialReport;