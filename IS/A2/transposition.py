def encrypt(message, key):
    cipher = [""] * key
    for col in range(key):
        pointer = col
        while pointer < len(message):
            cipher[col] += message[pointer]
            pointer += key
    return "".join(cipher)

def decrypt(cipher, key):
    n = len(cipher)
    num_cols = key
    num_rows = n // key
    num_shaded = n % key

    plaintext = [""] * num_rows
    col = 0
    row = 0

    for ch in cipher:
        plaintext[row] += ch
        row += 1

        if (row == num_rows) or (row == num_rows - 1 and col >= num_cols - num_shaded):
            row = 0
            col += 1

    return "".join(plaintext)


# Main
message = input("Enter message: ")
key = int(input("Enter key: "))

cipher = encrypt(message, key)
print("Encrypted:", cipher)

decrypted = decrypt(cipher, key)
print("Decrypted:", decrypted)

#columnar 