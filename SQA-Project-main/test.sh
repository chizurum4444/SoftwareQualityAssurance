#!/bin/bash 
cd "Phase-2/Frontend"
javac Transaction.java
cd "../.."

SCRIPT=$(readlink -f "$0")

SCRIPTPATH=$(dirname "$SCRIPT")

echo "Testing java execution"
cd "Phase-1/tests/createTests/createInputs"
echo $SCRIPTPATH
java -classpath ../../../../Phase-2/Frontend Transaction ../../../../Phase-2/Frontend/userAccount.txt ../../../../Phase-2/Frontend/availableTickets.txt < test.input > ../createTestResults/test.output