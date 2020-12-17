# $1: alias name of certificate (CN)
# $2: keystore name
cd src/main/resources/keystore/ || exit
keytool -import -alias ${1} -file ${1}.crt -keystore ${2}.p12
echo "Done"