#!/bin/bash
# Compile java files
javac Transaction.java
javac DailyTransaction.java

# Setup user accounts and available tickets file
cd ../backend
cp ua-backup.txt userAccount.txt
cp at-backup.txt availableTickets.txt
cd ../frontend

# Run test
java Transaction < input.txt

