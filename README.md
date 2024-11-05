# P2P File Sharing System

## Introduction
The P2P File Sharing System facilitates direct file exchange between peers without a central server. It operates via sender and receiver threads, utilizing sockets and object serialization for communication.

## Prerequisites
- JDK (Java Development Kit) installed locally.
- Both sender and receiver systems must be connected to the same Wi-Fi network.
- Determine the IPv4 address of each system using the `ipconfig` command (on Windows) or `ifconfig` command (on Unix-based systems).
- Update the sender and receiver IP addresses in the code to match the determined IPv4 addresses.
- For Run in Local System u can use the `127.0.0.1` in both SenderIp and ReceiverIp addresses
- Also Need to Change the  `FileToSend String Array ` Which File u Want to send.
- Both sender and receiver  Must be connected to the same  Port 


## How to Run
Compile: `javac P2PFileSharingSystem.java`
Run: `java P2PFileSharingSystem`

## Features
- Simulated sender and receiver components.
- Transmission of files over a network.
- Error handling and graceful exit mechanisms.

## Methodology
- Sender function sends files to the receiver.
- Receiver function saves received files.
- Multithreading for concurrent operation.

## Results
Successful transmission and reception of files are displayed in the console.

## Future Enhancements
- Dynamic IP handling.
- Security measures like encryption.
- Improved error management.

## Conclusion
The P2P File Sharing System offers a decentralized approach to file exchange, showcasing the power of Java's networking capabilities.

## Output Details

Upon running the P2P File Sharing System, the following output behavior is observed:

1. **Automatic Download Directory Creation:**
   - The system automatically generates a designated download directory upon execution.

2. **File Sharing and Addition:**
   - Shared files are seamlessly added to the download directory.
   - If a file with the same name is sent again, the system updates the existing file with any new content.

This output mechanism ensures efficient management of shared files and facilitates seamless updates to existing content.