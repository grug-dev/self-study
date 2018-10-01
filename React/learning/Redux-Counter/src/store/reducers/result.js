import * as constants  from '../actions';

const initialState = {
    results: []
}



const resultsReducer = (state = initialState , action) => {   
    switch (action.type) {                
        case constants.STORE_RESULT:
            const newResults = [...state.results , action.payLoad.currentCounter];  
            console.log('New Results', newResults)          ;
            return {
                ...state,
                results: newResults
            }    
        case constants.DELETE_RESULT:            
            const myArray =  [...state.results];
            const index = action.payLoad.index;
            myArray.splice(index, 1);
            return {
                ...state ,
                results: myArray
            };
        default:
            break;
    }    
    return state;
}


export default resultsReducer;