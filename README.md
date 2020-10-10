# CodeExamples

##Ex 1: Adding NTLM authentication support to CloseableHttpClient .
  NTLM is a challenge-response authentication protocol, the client is able to prove its identity without sending a password to the server,
  instead it sends the user name and domain name to the domain controller(DC) and uses the password to encrypt a nonce value 
  which will be used for the user authentication.
   - TestCase: submitNTLMAuthRequest in AppTest class
   - Class: NtlmAuthImplemetation
