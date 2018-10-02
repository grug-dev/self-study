import * as constants  from '../actions/actionTypes';

const initialState = {
    counterRedux : 0    
}



const counterReducer = (state = initialState , action) => {     
    
    switch (action.type) {        
        case constants.INCREMENT: 
        case constants.SUBSTRACT:
        case constants.DECREMENT:
        case constants.ADD:              
            return { 
                ...state , 
                counterRedux: state.counterRedux + action.payLoad.value
            }     
        default:
            break;
    }    
    return state;
}


export default counterReducer;