<?php
$parameter = $_GET['waId'];
$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, "YOUR_AUTH_LINK_from_Otpless");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);

$headers = array(
    'clientId: YOUR_CLIENT_ID',
    'clientSecret: YOUR_CLIENT_SECRET',
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
