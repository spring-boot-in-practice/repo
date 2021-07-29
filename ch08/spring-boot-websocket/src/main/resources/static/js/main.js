// Create a WebSocket connection at http://localhost:8080/ws
let sock = new SockJS('http://localhost:8080/ws');

// Create a new StompClient object with the WebSocket endpoint
let client = Stomp.over(sock);

// Start the STOMP communications, provide a callback for when the CONNECTED frame arrives.
client.connect({}, (frame) => {
	// Subscribe to "/topic/messages". Whenever there is a new message, add the text in a list-item element in the unordered list.
	console.log("Frame is: " +frame);
	client.subscribe('/topic/messages', (payload) => {
		let message_list = document.getElementById('message-list');
		let message = document.createElement('li');
		let output = JSON.parse(payload.body);
		message.appendChild(document.createTextNode(output.content +" at " +output.time));
		message_list.appendChild(message);
	});
});

// Function to send message. This function is invoked while you click on the
// Send in the HTML page. It takes the value in the ‘message-input’ text field 
// and send it to the server with empty headers ({}).
function sendMessage() {
	console.log("Sending message");
	let input = document.getElementById('message-input');
	client.send('/app/chat', {}, JSON.stringify({ content: input.value }));
}
