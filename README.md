# DataProcessing

Assumptions:
1) The JSON structure coming from client side is in the for of a string
2) Account Id is present in all JSON structure
3) To present the modified view, creating file with modified values in the JSON structure.

Questions/Answers:

1. How does your solution handle malformed or corrupt data?

Answer: I have written a function "validateIncomingJsonString", which first validates the JSON string by checking all its parameters and if its proper then only the system starts parsing it. I have not added any validation for the values of the parameters like validation on length of account id, length of first name, last name, format of event_date etc. However, I can enhance my function to add the validation for values too.
Like this my solution is able to handle the malformed and corrupt data.

2. Is your solution optimized for query latency or throughput?

Answer: Yes my solution is optimized for query latency or throughput. To specifically test the throughput I have written a unit test "DataProcessingUnitTest3" which send 1000 modified JSON structures to the system for processing. The system is able to process all those 1000 JSON structures in 17.759 secods i.e approx 56 queries per second, which is good.

3. What would you do differently if the client doesn’t send the account ID?
4. If the view gets very large (can no longer fit into memory), how can we modify it to ensure we’re still able to look up examples?
