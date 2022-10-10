# Compile main java file
cd "Phase-2/Frontend"
javac Transaction.java
cd "../.."
# Enter the phase 1 dir to get access to the tests
cd "Phase-1/tests"

echo "Starting script..."

# Loop through all of the test directories
for dir in *; do 
    cd $dir
    echo "Running tests in $dir"
    # Find the input directories
    for subDir in *; do
        count=0
        # Check if the sub directory is an input directory
        if [[ $subDir =~ ^[a-zA-z]+I[?=n] ]]; then
            # Go into sub dir
            cd $subDir
            outputDir="${subDir%%I*}TestResults"
            # Loop through all input files
            for file in *; do
                if [[ $file =~ \.[?=i][?=n][?=p][?=u][?=t] ]]; then
                    let "count++"
                    newFile="${file%%\.*}.output"
                    # printf "newFile = $newFile, outputDir = $outputDir \n"
                    # Run test
                    java -classpath ../../../../Phase-2/Frontend Transaction ../../../../user_accounts.txt ../../../../rental_units.txt < $file > ../$outputDir/$newFile
                fi
            done
            cd ..
            
        fi
    done
    cd ..
    printf "\n"
done

# Compare test results to predicted outputs
for dir in *; do
    cd $dir
    echo "Comparing outputs in $dir"
    
    outputDir="${dir%%T*}Outputs"
    testRes="${dir%%T*}TestResults"
    diff -r $outputDir $testRes

    cd ..
done

cd "Phase-1/tests/deleteTests"
diff -r deleteOutputs deleteTestResults