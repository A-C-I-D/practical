text = "Hello World"

print(f"{'Char':<6}{'ASCII':<8}{'AND 127':<10}{'Result(AND)':<15}{'XOR 127':<10}{'Result(XOR)':<15}")
print("-" * 64)

for ch in text:
    ascii_val = ord(ch)
    and_val = ascii_val & 127
    xor_val = ascii_val ^ 127

    and_char = chr(and_val)

    xor_char = chr(xor_val)
    if xor_val < 32 or xor_val == 127:
        xor_char = "NP"  # Non-Printable

    print(f"{repr(ch):<6}{ascii_val:<8}{and_val:<10}{and_char:<15}{xor_val:<10}{xor_char:<15}")