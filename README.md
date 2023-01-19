# OOP1_project
Nondeterministic finite state machine 
https://replit.com/@tedonikolov/OOP1project?v=1
<p>The program uses home made Console similar to command prompt. To check all supported commands can be typed: <b><i>help</i></b></p>
<a href="https://www.linkpicture.com/view.php?img=LPic63c97fabaa3441022312171"><img src="https://www.linkpicture.com/q/Screenshot_20230119_073553.png" type="image"></a>
<p> Before using the menu and all others, a xml file must be open.</p>
<a href="https://www.linkpicture.com/view.php?img=LPic63c9803bcc5c12045125268"><img src="https://www.linkpicture.com/q/Screenshot_20230119_073850.png" type="image"></a>

<p> Once the file is open the menu of the program can be open</p>

![image](https://user-images.githubusercontent.com/100678443/213521336-20d12212-3aeb-4f7f-88f9-23cd9d24c343.png)


<p>The current file contains the following automation, which can be present by command <i>print</i>:</p>

![image](https://user-images.githubusercontent.com/100678443/213520569-77fc1825-a641-4057-a8c2-8dc7168a45b3.png)

<p>Command <i>recognize</i> checks if a given string is from the automation:</p> 

![image](https://user-images.githubusercontent.com/100678443/213522359-4d6d481f-ba94-4e2a-b644-3cee96652458.png)
![image](https://user-images.githubusercontent.com/100678443/213522371-b8459be7-1540-4b90-8a03-53a047f97f21.png)

<p>Command <i>deterministic</i> checks if the automation is determined:</p> 

![image](https://user-images.githubusercontent.com/100678443/213526149-31f8668c-f423-4217-a186-f981055b8f6e.png)

<p> Command <i>mutator</i> is used for creating one nondeterministic to deteministic automation. For example:</p>

![image](https://user-images.githubusercontent.com/100678443/213526517-c37b3c9c-fb4e-492f-9980-f127098e4a0d.png)
![image](https://user-images.githubusercontent.com/100678443/213526527-f3e09754-6d64-4f1f-b27b-a5e6b4dd06a5.png)
<p>Which created the following automation:</p>

![image](https://user-images.githubusercontent.com/100678443/213526852-20077248-3e55-4764-8044-405ba1ebc5c2.png)

<p>In order to create a new automaton, we use the </i>reg</i> command and writing the regular expression from which we want to obtain the automaton.</p>

![image](https://user-images.githubusercontent.com/100678443/213527237-f11e1c30-5d5a-4f66-950a-8cf3edf9a4e2.png)
<p>This creates the following automation:</p>

![image](https://user-images.githubusercontent.com/100678443/213527347-e27b0693-60ac-4ac2-a59e-3c29682aaaa5.png)

<p>Which represents</p>

![image](https://user-images.githubusercontent.com/100678443/213527469-9127bb91-d3ac-464b-aeff-e6e23e8445cf.png)

<p>Command <i>union</i> creates union of two automations. For exmaple:</p>

![image](https://user-images.githubusercontent.com/100678443/213527952-17ac6884-0fc9-44c4-a574-59a8f11a9479.png)
<p>Which creates the following combination:</p>

![image](https://user-images.githubusercontent.com/100678443/213528030-a5ade688-17ac-4084-ac22-4a01eeded09a.png)
![image](https://user-images.githubusercontent.com/100678443/213528253-3e4a688f-ecf1-4f4f-990b-4d3f90e1bd24.png)

<p>Command <i>concat</i> creates concatenation of two automations. For exmaple:</p>

![image](https://user-images.githubusercontent.com/100678443/213528554-497cbc2f-c0bd-4788-b2d1-199a0a4bc5a4.png)
<p>Which creates the following combination:</p>

![image](https://user-images.githubusercontent.com/100678443/213528595-3bb526e5-ac7e-4572-b5cf-51168b0825e7.png)
![image](https://user-images.githubusercontent.com/100678443/213528721-0389116c-591f-46fd-81c3-6cf131b8a85f.png)

<p>Almost all the operations given as a task have been completed, except for finding the positive envelope of an automaton. The reason: the impossibility of finding an answer to the question of what is a positive shell and, accordingly, how to perform the operation. The created program, with slight improvements, would be useful for students of the Discrete Structures discipline in order to solve finite state automata problems.</p>
