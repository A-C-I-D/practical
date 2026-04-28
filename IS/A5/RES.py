try:
    from Crypto.PublicKey import RSA
    from Crypto.Cipher import PKCS1_v1_5
    from Crypto.Random import get_random_bytes
except ImportError:
    print("This program requires pycryptodome. Install it with: pip install pycryptodome")
    raise

import binascii


def generate_keypair(key_size=1024):
    key = RSA.generate(key_size)
    public_key = key.publickey()
    private_key = key
    return public_key, private_key


def encrypt_message(plaintext, public_key):
    cipher = PKCS1_v1_5.new(public_key)
    ciphertext = cipher.encrypt(plaintext.encode("utf-8"))
    return binascii.hexlify(ciphertext).decode()


def decrypt_message(ciphertext_hex, private_key):
    cipher = PKCS1_v1_5.new(private_key)
    ciphertext = binascii.unhexlify(ciphertext_hex)
    plaintext = cipher.decrypt(ciphertext, None)
    if plaintext is None:
        return None
    return plaintext.decode("utf-8")


def save_keys(public_key, private_key, filename="rsa_keys"):
    with open(f"{filename}_public.pem", "wb") as f:
        f.write(public_key.export_key())
    with open(f"{filename}_private.pem", "wb") as f:
        f.write(private_key.export_key())
    print(f"Keys saved: {filename}_public.pem, {filename}_private.pem")


def load_keys(filename="rsa_keys"):
    with open(f"{filename}_public.pem", "rb") as f:
        public_key = RSA.import_key(f.read())
    with open(f"{filename}_private.pem", "rb") as f:
        private_key = RSA.import_key(f.read())
    return public_key, private_key


def main():
    print("=== RSA Encryption/Decryption ===")
    choice = input("(G)enerate new keys, (L)oad existing keys, or (Q)uit? ").strip().lower()

    if choice == "g":
        print("Generating 1024-bit RSA key pair...")
        public_key, private_key = generate_keypair(1024)
        print("Keys generated successfully!")
        save = input("Save keys to file? (Y/N): ").strip().lower()
        if save == "y":
            save_keys(public_key, private_key)
    elif choice == "l":
        try:
            public_key, private_key = load_keys()
            print("Keys loaded successfully!")
        except FileNotFoundError:
            print("Key files not found. Generate new keys first.")
            return
    else:
        print("Exiting.")
        return

    while True:
        mode = input("\n(E)ncrypt, (D)ecrypt, or (Q)uit? ").strip().lower()

        if mode == "e":
            plaintext = input("Enter message to encrypt: ")
            ciphertext = encrypt_message(plaintext, public_key)
            print("Encrypted (HEX):", ciphertext)
        elif mode == "d":
            ciphertext_hex = input("Enter ciphertext (hex): ").strip()
            plaintext = decrypt_message(ciphertext_hex, private_key)
            if plaintext is None:
                print("Decryption failed. Invalid ciphertext or key.")
            else:
                print("Decrypted message:", plaintext)
        elif mode == "q":
            print("Exiting.")
            break
        else:
            print("Unknown option.")


if __name__ == "__main__":
    main()
