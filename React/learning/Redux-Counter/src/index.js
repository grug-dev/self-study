import React from 'react';
import ReactDOM from 'react-dom';



import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import counterReducer from './store/reducers/counterReducer';
import resultReducer from './store/reducers/resultReducer';


// Redux
import { createStore  , combineReducers , applyMiddleware , compose} from "redux";
import { Provider } from 'react-redux';

// Redux Thunk
import thunk from 'redux-thunk';

const rootReducer = combineReducers({
    ctr: counterReducer,
    res: resultReducer
});

const loggerMiddleware = ( store ) => {
    return ( next ) => {
        return (action) => {
            console.log('[MiddlewareLogger] Dispatching:' , action);
            const result = next(action);
            console.log('[MiddlewareLogger] Next State:' , store.getState());
            return result;
        }
    }
};

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

// Store using ReduxDevTools
const store = createStore( rootReducer, 
                            /* preloadedState, */
                         composeEnhancers(
                              applyMiddleware(loggerMiddleware , thunk) 
                          ) );

//   const store = createStore( rootReducer , applyMiddleware(loggerMiddleware));

ReactDOM.render(<Provider store={store}><App /></Provider>, document.getElementById('root'));
registerServiceWorker();
