# $1 : alias
# $2 : keystore name
# $3 : keystore and key password
cd src/main/resources/keystore/ || exit
keytool -genkeypair -alias ${1} -keyalg RSA -keystore ${2}.p12 \
          -validity 3650 -storetype PKCS12 -keysize 2048 \
          -dname "CN=${1}, OU=${1} Unit, O=${1} Org, L=Unknown, ST=Unknown, C=IN" \
          -keypass ${3} -storepass ${3}
# keytool -genkeypair -alias ${1} -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ${2}.p12 -validity 3650 -storepass ${3}
keytool -list -v -storetype pkcs12 -keystore ${2}.p12
echo "Done"