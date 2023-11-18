import { useState } from "react";


function MyArray(){
   let[arr,setArry ]=useState([5,3,8,2,9,1,7]);
   const sortArray=(direction)=>{
    if(direction==='ASC'){
        let temp=arr.sort((a,b)=>a-b);
        setArry([...temp])
    }
    else{
        let temp=arr.sort((a,b)=>b-a);
        setArry([...temp])
    }
   }
    return(
        <div>
            <h5>My Array:</h5>

            {
                arr.map((e,index)=>
                <span key={index} className="arry">
                    {e}
                </span>
                )
            }

            <br />
            <button onClick={()=>sortArray('ASC')}>sort Asc</button>
            &nbsp;&nbsp;
            <button onClick={()=>sortArray('DESC')}>sort Desc</button>

        </div>

    );
}
export default MyArray;
