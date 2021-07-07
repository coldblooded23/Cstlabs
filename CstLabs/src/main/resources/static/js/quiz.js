/**
 * 
 */

function startQuiz(){
var quizCon =document.getElementById("quizCon");
var quizQuestions=document.getElementById("questionBox");
var hiddenFrame=document.getElementById("hiddeniframe");
var submit=document.getElementById("submited");
var dummyButton=document.getElementById("dummyButton");
var navTime =document.getElementById("SidenavTimer");
navTime.style.display='block';
quizCon.style.display='none';
quizQuestions.style.display='block';

removeAtagHerf();


const StartingMin=30;
let time=StartingMin*60;
	setInterval(function(){
	const timeleft=document.querySelector('#time-left');
const min =Math.floor(time/60);
let sec =time%60;
let zero="0";
if(min==20&&sec==0){ 
dummyButton.click();
}
if(min==10&&sec==0){ 
dummyButton.click();
}
if(min==5&&sec==0){
var modelButton =document.getElementById("modelButton");
modelButton.click();
}

if(min==0&&sec==0){

	navTime.style.display='none';
LeftOverQuestions();
	/*submit.click();*/
	
	navTime.style.display='none';
}
if(sec<10){
timeleft.innerHTML=`${min}:${zero+sec}`;
}else{
timeleft.innerHTML=`${min}:${sec}`;
}
time--;
},1000);
};









function verrifynull(){
const totalcount=document.getElementById("totalCount").value;
var submit=document.getElementById("submited");
for(let i=1;i<=totalcount;i++){
let question ="opt"+i;
let questName="quesName"+i;
var val =document.getElementsByName(question);
var namer =document.getElementById(questName).textContent;

if(val.length==4){
if ( ( val[0].checked == false ) && ( val[1].checked == false )&& ( val[2].checked == false )&& ( val[3].checked == false ) )
{
val[0].focus();
console.log("namer");
 window.alert('Please answer all the questions!');  
return false;
}}else if(val.length==2){
	
if ( ( val[0].checked == false ) && ( val[1].checked == false ) )
{
val[0].focus();
console.log("namer");
 window.alert('Please answer all the questions!');  
return false;
}	
}

}
submit.click();
};



function LeftOverQuestions(){
const totalcount=document.getElementById("totalCount").value;
var submit=document.getElementById("submited");
let j=0;
for(let i=1;i<=totalcount;i++){
let question ="opt"+i;
let questName="quesName"+i;
var val =document.getElementsByName(question);
var namer =document.getElementById(questName).textContent;
if(val.length==4){
if ( ( val[0].checked == false ) && ( val[1].checked == false )&& ( val[2].checked == false )&& ( val[3].checked == false ) )
{
j++;
}}else if(val.length==2){
	
if ( ( val[0].checked == false ) && ( val[1].checked == false ) )
{
j++;
}	
}
}
document.getElementById("Unanswered").value=j;

console.log("un answered questions" +j);
submit.click();
};




function removeAtagHerf(){
	console.log('removeAtagHerf()');
 var anchors = document.querySelectorAll('a[href]');
    anchors.forEach(function(a) {
        a.href = "#";
        console.log("Hyperlink Changed");
    });
};









