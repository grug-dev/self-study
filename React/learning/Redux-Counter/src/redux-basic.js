// This file will be executed by Node.
const redux = require('redux');
const createStore = redux.createStore;


const INCREMENT_COUNTER = 'INCREMENT_COUNTER';
const ADD_COUNTER = 'ADD_COUNTER';

const initialState = {
    counter: 0
}

// Reducer
const rootReducer = ( currentState = initialState, action) => {

    switch (action.type) {
        case INCREMENT_COUNTER:
        return {
            ...currentState,
            counter: currentState.counter + 1
        }
        case ADD_COUNTER:
        return {
            ...currentState,
            counter: currentState.counter + action.value
        }
        default:
        break;
    }

    return currentState;
};


// Store
const store = createStore( rootReducer);
console.log(store.getState());

// Subscription
// The subscription must be before the dispatching, 
// otherwise you'll miss that dispatch as the subscription is set up too late.
store.subscribe( () => {
    console.log('[Subscription' , store.getState());
} );


// Dispatching Action
store.dispatch( {type: INCREMENT_COUNTER} );
store.dispatch( {type: ADD_COUNTER , value : 10 });
console.log(store.getState());




