import React, { Component } from 'react';
import CounterControl from '../../components/CounterControl/CounterControl';
import CounterOutput from '../../components/CounterOutput/CounterOutput';
import * as actionTypes  from '../../store/actions';

// Redux
import { connect} from 'react-redux';

class Counter extends Component {
   


    render () {
        const results = (
            this.props.storedResults.map( (item,key) => {
                return (<li key={key} style={{
                    cursor: 'pointer',
                    
                }} onClick={ () => this.props.onDeleteResults(key)}>{item}</li>)
            })                        
        );

        return (
            <div>
                <CounterOutput value={this.props.compCounter} />
                <CounterControl label="Increment" clicked={ this.props.onIncrementCounter} />
                <CounterControl label="Decrement" clicked={this.props.onDecrementCounter}  />
                <CounterControl label="Add 5" clicked={ this.props.onAddCounter}  />
                <CounterControl label="Subtract 5" clicked={this.props.onSubstractCounter}  />
                <hr />
                <button onClick={ () => this.props.onStoreResults(this.props.compCounter)}>Store Result</button>
                <ul>
                    {results}
                </ul>
            </div>
        );
    }
}

// map to store instructions about how the state managed by redux should be mapped to props you
// can see the container. The state is the state we set up previously in the reducer.

// It expects the state input and returns a JS object which is a map of prop names and slices of the 
// state stored. It's our way of configuring which kind of information we need.
const mapStateToProps =  (state) => {
    return {
        compCounter: state.ctr.counterRedux ,
        storedResults: state.res.results
    };
}

// Which kind of actions do I want to dispatch in thise container.
//  It returns prop names referencing to a function which should eventually get executed to a dispatch
// an action.
const mapDispatchToProps =  ( dispatch) => {
    return {
        onIncrementCounter: () => dispatch({ type: actionTypes.INCREMENT , payLoad: {
            value : 1
        }}) ,
        onAddCounter: () => dispatch({ type: actionTypes.ADD , payLoad: {
            value : 5
        }}) ,
        onDecrementCounter: () => dispatch({ type: actionTypes.DECREMENT , payLoad: {
            value : -1
        }}),
        onSubstractCounter: () => dispatch({ type: actionTypes.SUBSTRACT , payLoad: {
            value : -5
        }}) ,
        onStoreResults: ( currentCounter) => dispatch ({
            type: actionTypes.STORE_RESULT ,
            payLoad: {
                currentCounter: currentCounter
            }
        }) ,
        onDeleteResults: ( id ) => dispatch ({
            type: actionTypes.DELETE_RESULT ,
            payLoad: {
                index : id
            }
        }) 
    }
};


export default connect(mapStateToProps, mapDispatchToProps)(Counter)