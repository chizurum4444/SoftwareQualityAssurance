# Compile java files
javac Transaction.java
javac DailyTransaction.java
javac User.java
javac AvailableTickets.java

# setup old daily transaction
cd ../backend
cp dt-backup.txt dailytransaction.output
cd ../frontend

# run daily script 5 times
for i in {1..5}; do
    echo ""
    echo "~~~ Daily test $i ~~~"
    sh daily.sh
done

# Main java file automatically merges daily outputs
# Read in daily transactions
cd ../backend
cat dailytransaction.output
echo ""
cd ../frontend

