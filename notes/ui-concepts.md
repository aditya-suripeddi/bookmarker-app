1. [Single Page Applications](https://en.wikipedia.org/wiki/Single-page_application):


              a. previously applications used to return entire index.html whenever 
                 user performed page transitions on UI 


              b. browser which is a client gets this index.html of "Content-Type: text/html" (for google.com)
                 from the server and renders it 


              c. most of the time the navbar/header and other parts of html does not change
                 so in single page applications, the react/javascript code that we write sits with browser
                 client (like a plugin for our server) and exchanges json data for most parts and 
                 updates the html / presentation of the page with code on the browser 
          
   Refer: [Introduction to ReactJs & NextJs](https://youtu.be/x5KMRG3bt1Q)

<br>

2. [Quick Starter: Covers 80% Concepts of React you use DAILY](https://react.dev/learn)

<br>

3. React setup and run commands  

   ```bash
    
    # setup project <appName> with typescript files instead of javascript 
    $ npx create-react-app <appName> --template typescript
   
    # run the app
    $ cd <appName>
    /path/to/appName $ npm start
   ```
<br>

4. A React component should return a JSX with exactly one root node

   ```jsx
        // A JSX component should have a single root node
        // if you have multiple root nodes, like:
         
           return (
              <p> Root1 </p>
              <p> Root2 </p>
           )
         
        // then wrap them inside a div:
        
        
           return (
             <div>
                <p> Root1 </p>
                <p> Root2 </p>
             </div>
           )
   ```
<br>


5.  [reactrouter.com](http://reactrouter.com) website and content is updated from what is shown in 
    [Intro to ReactJS & NextJS](https://youtu.be/x5KMRG3bt1Q). The command to setup react-router
    from the [Intro to ReactJS & NextJS](https://youtu.be/x5KMRG3bt1Q) is: 
    ```bash
     $ npm install react-router-dom@6
    ```

6. [NextJS](https://nextjs.org/) is a framework built on React (webpage says it is <em>React framework</em>)
   One of the primary reasons people opt for NextJS is server-side rendering.
   React offers client-side rendering which may not work well when building public facing applications
   which need SEO support. With NextJS there are some optimizations that can be leveraged due to server
   side rendering for initial page loads and based on components/page configurations it either performs client-side or
   server side rendering.
  
  ```bash
    # command to create nextjs app with typescript
    $ npx create-next-app <appName> --ts 
  ```
   follow the choices shown [*here](https://stackoverflow.com/a/76318637) 

   ```tsx
        import {NextPage} from "next";
       
        const Home: NextPage = () => {
             return (
                   <div>
                      <h1>Welcome to Bookmarker UI (NextJS)</h1>
                   </div>
             )
        }
       
        export default Home
   ```