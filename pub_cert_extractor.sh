# $1 : alias CN of certificate
# $2 : key-store name
# $3 : password
cd src/main/resources/keystore/ || exit
#openssl pkcs12 -in ${1}.p12 -nokeys -out ${2}
keytool -exportcert -alias ${1} -keypass keypass \
   -keystore ${2}.p12 -storepass ${3} -file ${1}.crt -rfc
keytool -printcert -file ${1}.crt
echo "Done"
