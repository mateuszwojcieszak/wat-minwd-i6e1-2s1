import React from 'react'
import styled from 'styled-components'
import { Card, Form, Input, Button, InputNumber } from 'antd'

const FormCard = styled(Card)`
  width: 100%;
  height: 150px;
  margin: 0 auto; 
  text-align: center;
`;

const FormTwitter = ({ form, setFormData }) => {
    const { getFieldDecorator } = form
    const onSubmit = e => {
        e.preventDefault();
        form.validateFields((err, values) => {
            if (!err) {
                setFormData(values)
            }
        });
    }

    return (
        <FormCard title="Inser username and number of last twits" >
            <Form onSubmit={onSubmit} layout='inline'>
                <Form.Item label="Username">
                    {getFieldDecorator('username', {
                        rules: [{ required: true, message: 'Please input username!' }],
                    })(
                        <Input />
                    )}
                </Form.Item>
                <Form.Item label="Quantity">
                    {getFieldDecorator('quantity', {
                        rules: [{ required: true, message: 'Please input quantity!' }],
                    })(
                        <InputNumber min={1} max={10} />
                    )}
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">Submit</Button>
                </Form.Item>
            </Form>
        </FormCard>
    )
}

const WrappedNormalLoginForm = Form.create({ name: 'normal_login' })(FormTwitter)

export default WrappedNormalLoginForm
