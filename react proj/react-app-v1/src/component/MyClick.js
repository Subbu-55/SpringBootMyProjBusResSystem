import { useState } from "react";
function MyClick(){

    let [product,setProduct]=useState('');
  let [msg,setMsg]=useState('');

  const displayProduct=(category)=>{

    if(category ==='mobile')
       setProduct('Apple Iphone 14,OnePlus 12')

    else
    setProduct('HP Envy,MackBook Air')

  }

  const display=(hi)=>{

    if(hi === 'morning'){
      setMsg('Good Morning')
    }
    else
    setMsg('Good Evening')
     
  }

    return(
        <div className="App">
     <h1>My First React App</h1>

     <div className='content'>{msg}</div>
     <br />
     <div>
      <button onClick={()=>display('morning')}>Wish morning</button>
      &nbsp;&nbsp;
      <button onClick={()=>display('evening')}>Wish evening</button>
     </div>

<hr />

     <div>
      <p>
        What you want to buy
      </p>
      <button onClick={()=>displayProduct('mobile')} >Mobile</button>
      <button onClick={()=>displayProduct('laptop')} >Laptop</button>
     </div>
     <br />
     <div className='content'>{product}</div>
     


     
         </div>

    );
}

export default MyClick;