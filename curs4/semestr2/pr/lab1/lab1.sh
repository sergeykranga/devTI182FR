#!/usr/bin/env bash

dns_server="8.8.8.8"
domain_name=""
ip=""

PS3='Enter your choice: '
options=("DNS server IP (default is $dns_server)" "Domain name" "IP (reverse lookup)" "Quit")

select opt in "${options[@]}"
do
    case $opt in
        "DNS server IP (default is $dns_server)")
            printf "Enter a new DNS server IP: "
            read -r dns_server
            echo "Set DNS server IP to $dns_server"
            ;;
        "Domain name")
            printf "Enter domain name to lookup: "
            read -r domain_name
            break
            ;;
        "IP (reverse lookup)")
            printf "Enter IP address to reverse lookup: "
            read -r ip
            break
            ;;
        "Quit")
            break
            ;;
        *) echo "invalid option $REPLY";;
    esac
done

echo "Will be using DNS server: $dns_server"

if [ -n "$domain_name" ]; then
    ip_addresses="$(dig @"$dns_server" +short "$domain_name")"
    echo -e "$domain_name resolves to:\n$ip_addresses"
elif [ -n "$ip" ]; then
    domain_names="$(dig @"$dns_server" +short -x "$ip")"
    echo -e "$ip address belongs to domain name:\n$domain_names"
fi
