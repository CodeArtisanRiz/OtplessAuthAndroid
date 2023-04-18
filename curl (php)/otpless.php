<?php
$parameter = $_GET['waId'];
$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "Your_Auth_Link_from_Otpless");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);

$headers = array(
    'clientId: xzqqr8x9',
    'clientSecret: 7lk9e31gp9rucbyn',
    'Content-Type: application/json'
);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

$data = array(
    'waId' => $parameter
);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));

$result = curl_exec($ch);
if(curl_errno($ch)) {
    echo 'Error: ' . curl_error($ch);
} else {
    echo $result;
}

curl_close($ch);
?>
