try:
    from Crypto.Cipher import DES
    from Crypto.Util.Padding import pad, unpad
    from Crypto.Random import get_random_bytes
except ImportError:
    print("This program requires pycryptodome. Install it with: pip install pycryptodome")
    raise

import binascii

BLOCK_SIZE = DES.block_size


def get_key_bytes():
    while True:
        key = input("Enter DES key (8 characters): ").strip()
        key_bytes = key.encode("utf-8")
        if len(key_bytes) == 8:
            return key_bytes
        print("Invalid key length. DES key must be exactly 8 characters.")


def encrypt(plaintext, key):
    iv = get_random_bytes(BLOCK_SIZE)
    cipher = DES.new(key, DES.MODE_CBC, iv)
    ciphertext = cipher.encrypt(pad(plaintext.encode("utf-8"), BLOCK_SIZE))
    return iv + ciphertext


def decrypt(ciphertext, key):
    iv = ciphertext[:BLOCK_SIZE]
    cipher = DES.new(key, DES.MODE_CBC, iv)
    plaintext = unpad(cipher.decrypt(ciphertext[BLOCK_SIZE:]), BLOCK_SIZE)
    return plaintext.decode("utf-8")


def main():
    key = get_key_bytes()
    choice = input("Choose mode: (E)ncrypt or (D)ecrypt: ").strip().lower()

    if choice == "e":
        plaintext = input("Enter plaintext: ")
        ciphertext = encrypt(plaintext, key)
        print("Encrypted (HEX):", binascii.hexlify(ciphertext).decode())
    elif choice == "d":
        hex_text = input("Enter ciphertext (hex): ").strip()
        try:
            ciphertext = binascii.unhexlify(hex_text)
        except (binascii.Error, ValueError):
            print("Invalid hex input.")
            return
        try:
            plaintext = decrypt(ciphertext, key)
            print("Decrypted plaintext:", plaintext)
        except ValueError:
            print("Decryption failed. Check the key and ciphertext.")
    else:
        print("Unknown mode. Please enter 'E' or 'D'.")


if __name__ == "__main__":
    main()
